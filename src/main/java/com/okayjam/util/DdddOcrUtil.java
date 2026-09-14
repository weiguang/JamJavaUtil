package com.okayjam.util;

import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Optional;

/**
 * 通用图片文字识别（OCR）工具类，基于 ONNX Runtime 运行 ddddocr 模型。
 *
 * <p>与 {@link TesseractOcrUtil} 的 Tesseract 方案互为补充；验证码等业务可基于它们二次封装（见 {@link VerifyCodeUtil}）。
 * 核心流程与 ddddocr 保持一致：
 * <ol>
 *     <li>预处理：等比缩放到高 {@value #INPUT_HEIGHT}，转灰度，像素除以 255 归一化，输出 CHW 布局</li>
 *     <li>推理：输入节点名从模型动态获取</li>
 *     <li>解码：对输出按时间步取 argmax，再做 CTC 贪心解码（合并相邻重复、去掉 blank）</li>
 * </ol>
 *
 * <p>使用前需要准备：
 * <ul>
 *     <li>模型文件 {@code common_old.onnx}（约 13MB），默认放 {@code models/common_old.onnx}
 *         （可用 {@code -Dddddocr.model.path=xxx} 覆盖）</li>
 *     <li>字符集文件，默认 {@code models/charset_old.txt}（可用 {@code -Dddddocr.charset.path=xxx} 覆盖）。
 *         ddddocr 默认模型的字符集为 {@code CHARSET_OLD}（含中文等共 8209 个字符，索引 0 为 CTC blank），
 *         与模型一一对应；若文件缺失则退化为内置的数字+字母字符集（仅适用于相应模型）。</li>
 * </ul>
 *
 * <p>模型与字符集获取（如需重新下载）：{@code pip download ddddocr} 得到 wheel 包，解压后取
 * {@code ddddocr/common_old.onnx}；字符集可用
 * {@code python -c "import ddddocr.charsets as c; open('charset_old.txt','w',encoding='utf-8').write(''.join(c.CHARSET_OLD[1:]))"} 导出。
 *
 * <p><b>更换为自己的模型</b>：把训练好的 ONNX 模型与配套 charset 放入 {@code models/}，通过 JVM 参数指定：
 * <pre>
 *   -Dddddocr.model.path=models/my_model.onnx
 *   -Dddddocr.charset.path=models/my_charset.txt
 * </pre>
 * 前提：模型输入为高度 64 的单通道灰度图，输出为 CTC 字符序列（{@code [T,1,C]} 或 {@code [1,T,C]}），
 * charset 索引 0 为 blank；若输入尺寸不同，需同步调整 {@link #INPUT_HEIGHT} 与预处理逻辑。
 *
 * @see <a href="https://github.com/sml2h3/ddddocr">sml2h3/ddddocr</a>
 */
public final class DdddOcrUtil {

    private static final Logger log = LoggerFactory.getLogger(DdddOcrUtil.class);

    /** 模型路径，可用 JVM 参数 -Dddddocr.model.path=xxx 覆盖 */
    private static final String MODEL_PATH =
            System.getProperty("ddddocr.model.path", "models/common_old.onnx");

    /** charset 文件路径，可用 JVM 参数 -Dddddocr.charset.path=xxx 覆盖 */
    private static final String CHARSET_PATH =
            System.getProperty("ddddocr.charset.path", "models/charset_old.txt");

    /** 内置兜底字符集：数字 + 小写 + 大写，仅适用于字符集为 62 位的模型 */
    private static final String FALLBACK_CHARSET =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /** 模型输入高度，ddddocr 内置 OCR 模型固定为 64 */
    private static final int INPUT_HEIGHT = 64;

    private static volatile OrtEnvironment env;
    private static volatile OrtSession session;
    private static volatile String inputName;
    private static volatile char[] charset;

    private DdddOcrUtil() {
    }

    /** 懒加载初始化 ONNX 环境、模型与字符集 */
    private static synchronized void ensureInitialized() throws OrtException, IOException {
        if (session != null) {
            return;
        }
        File model = new File(MODEL_PATH);
        if (!model.isFile()) {
            throw new IOException("ddddocr 模型不存在: " + model.getAbsolutePath()
                    + "，请下载 common_old.onnx 后放到该路径，或通过 -Dddddocr.model.path 指定");
        }
        env = OrtEnvironment.getEnvironment();
        session = env.createSession(model.getAbsolutePath(), new OrtSession.SessionOptions());
        inputName = session.getInputNames().iterator().next();
        charset = loadCharset();
        log.info("ddddocr 模型加载完成: {}，输入节点: {}，字符集大小: {}",
                model.getName(), inputName, charset.length - 1);
    }

    /** 字符集索引 0 保留给 CTC blank，真实字符从索引 1 开始 */
    private static char[] loadCharset() {
        String chars = FALLBACK_CHARSET;
        File file = new File(CHARSET_PATH);
        if (file.isFile()) {
            try {
                // 只去掉换行符，保留其余字符（字符集本身可能包含特殊符号）
                chars = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8)
                        .replaceAll("[\\r\\n]", "");
                log.info("已从 {} 加载 charset，共 {} 个字符", CHARSET_PATH, chars.length());
            } catch (IOException e) {
                log.warn("读取 charset 文件失败: {}，将使用内置兜底字符集", CHARSET_PATH, e);
            }
        } else {
            log.warn("charset 文件不存在: {}，将使用内置兜底字符集", CHARSET_PATH);
        }
        char[] table = new char[chars.length() + 1];
        table[0] = 0;
        chars.getChars(0, chars.length(), table, 1);
        return table;
    }

    /**
     * 识别图片文件中的文字（通用，验证码等短文本效果最佳）。
     *
     * @param imagePath 图片路径
     * @return 识别结果；图片不存在或无法解析时返回 {@link Optional#empty()}
     */
    public static Optional<String> ocr(String imagePath) throws OrtException, IOException {
        File file = new File(imagePath);
        if (!file.isFile()) {
            log.warn("图片不存在或不是文件: {}", imagePath);
            return Optional.empty();
        }
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
            log.warn("图片无法解析: {}", imagePath);
            return Optional.empty();
        }
        return ocr(image);
    }

    /**
     * 识别 {@link BufferedImage} 中的文字（通用）。
     *
     * @param image 待识别图片
     * @return 识别结果；入参为空时返回 {@link Optional#empty()}
     */
    public static Optional<String> ocr(BufferedImage image) throws OrtException, IOException {
        if (image == null) {
            return Optional.empty();
        }
        ensureInitialized();

        float[] data = preprocess(image);
        int width = data.length / INPUT_HEIGHT;
        long[] shape = {1, 1, INPUT_HEIGHT, width};

        try (OnnxTensor input = OnnxTensor.createTensor(env, FloatBuffer.wrap(data), shape);
             OrtSession.Result result = session.run(Collections.singletonMap(inputName, input))) {
            float[][][] output = (float[][][]) result.get(0).getValue();
            float[][] steps = toTimeSteps(output);
            int classes = steps.length == 0 ? 0 : steps[0].length;
            if (classes != charset.length) {
                log.warn("模型输出类别数({})与 charset 大小({})不一致，请确认 charset 是否与模型匹配",
                        classes, charset.length);
            }
            return Optional.of(decode(steps));
        }
    }

    /** 预处理：等比缩放到高 64、转灰度、像素除以 255，返回 CHW 浮点数组 */
    private static float[] preprocess(BufferedImage src) {
        int width = Math.max(1, (int) Math.round(src.getWidth() * (double) INPUT_HEIGHT / src.getHeight()));
        BufferedImage gray = new BufferedImage(width, INPUT_HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = gray.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(src, 0, 0, width, INPUT_HEIGHT, null);
        g.dispose();

        float[] data = new float[width * INPUT_HEIGHT];
        int i = 0;
        for (int y = 0; y < INPUT_HEIGHT; y++) {
            for (int x = 0; x < width; x++) {
                data[i++] = (gray.getRGB(x, y) & 0xFF) / 255f;
            }
        }
        return data;
    }

    /**
     * 兼容不同模型的输出布局，统一转换为 [时间步][类别数]：
     * 支持 [1, T, C]（batch 在前）与 [T, 1, C]（时间步在前，ddddocr 默认模型即是）。
     */
    private static float[][] toTimeSteps(float[][][] output) {
        if (output.length == 1) {
            return output[0];
        }
        if (output[0].length == 1) {
            float[][] steps = new float[output.length][];
            for (int t = 0; t < output.length; t++) {
                steps[t] = output[t][0];
            }
            return steps;
        }
        return output[0];
    }

    /** CTC 贪心解码：逐时间步取 argmax，合并相邻重复并去掉 blank(索引 0) */
    private static String decode(float[][] steps) {
        StringBuilder sb = new StringBuilder();
        int last = 0;
        for (float[] step : steps) {
            int index = 0;
            float max = step[0];
            for (int c = 1; c < step.length; c++) {
                if (step[c] > max) {
                    max = step[c];
                    index = c;
                }
            }
            if (index == last) {
                continue;
            }
            last = index;
            if (index != 0 && index < charset.length) {
                sb.append(charset[index]);
            }
        }
        return sb.toString();
    }

    /** 释放模型资源（应用退出时可选调用） */
    public static synchronized void close() {
        if (session != null) {
            try {
                session.close();
            } catch (Exception e) {
                log.warn("关闭 ddddocr session 失败", e);
            } finally {
                session = null;
            }
        }
    }
}
