package com.okayjam.util;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通用图片文字识别（OCR）工具类，基于 Tesseract（tess4j）。
 *
 * <p>本类只负责「把图片中的文字识别出来」，与验证码等具体业务无关；
 * 验证码等场景可基于它再做二次封装（见 {@link VerifyCodeUtil}）。
 * 另一套可选方案见 {@link DdddOcrUtil}（ddddocr/ONNX）。
 *
 * <p>模型目录：默认 {@code models}（与 ddddocr 模型统一存放），目录内需存在对应语言的
 * {@code *.traineddata}（如 {@code eng.traineddata}）；可用 JVM 参数 {@code -Dtesseract.datapath=xxx} 覆盖。
 *
 * <p><b>更换/新增语言模型</b>：从
 * <a href="https://github.com/tesseract-ocr/tessdata">tesseract-ocr/tessdata</a>
 * 下载对应的 {@code *.traineddata}（如简体中文 {@code chi_sim.traineddata}）放入模型目录，
 * 调用时指定语言即可：
 * <pre>{@code TesseractOcrUtil.ocr(image, "chi_sim", 3, null);}</pre>
 */
public final class TesseractOcrUtil {

    private static final Logger log = LoggerFactory.getLogger(TesseractOcrUtil.class);

    /** Tesseract 语言数据目录，默认 "models"（与 ddddocr 模型统一存放），可用 -Dtesseract.datapath=xxx 覆盖 */
    private static final String TESSDATA_PATH = System.getProperty("tesseract.datapath", "models");

    /** 默认识别语言 */
    private static final String DEFAULT_LANGUAGE = "eng";

    /** 默认页面分割模式：3 = PSM_AUTO（自动分析版面） */
    private static final int DEFAULT_PAGE_SEG_MODE = 3;

    /** 小图易误判，指定 DPI 更稳定 */
    private static final String DEFAULT_DPI = "300";

    /** 文档模式页面分割：6 = PSM_SINGLE_BLOCK，适合文档/代码截图这类连续文本块 */
    private static final int DOCUMENT_PAGE_SEG_MODE = 6;

    /**
     * 文档模式放大倍数：截图上的小字放大后识别率明显提升（实测 3 倍优于 2 倍）。
     * 注意保持彩色，不要转灰度——语法高亮的颜色区分有助于识别，转灰度反而变差。
     */
    private static final int DOCUMENT_UPSCALE = 3;

    /** 放大后允许的最大边长；超过则自动降低倍数，避免超大图放大后内存/耗时失控 */
    private static final int DOCUMENT_MAX_SIDE = 4000;

    /**
     * ITesseract 初始化代价较高且非线程安全：
     * 这里按「语言 + PSM + 字符白名单」缓存实例，并用 ThreadLocal 隔离线程。
     */
    private static final ThreadLocal<Map<String, ITesseract>> CACHE =
            ThreadLocal.withInitial(ConcurrentHashMap::new);

    private TesseractOcrUtil() {
    }

    /** 使用默认配置识别图片文件中的文字 */
    public static Optional<String> ocr(File image) {
        return ocr(image, DEFAULT_LANGUAGE, DEFAULT_PAGE_SEG_MODE, null);
    }

    /** 使用默认配置识别指定路径图片中的文字 */
    public static Optional<String> ocr(String imagePath) {
        return ocr(toFile(imagePath), DEFAULT_LANGUAGE, DEFAULT_PAGE_SEG_MODE, null);
    }

    /** 使用默认配置识别图片中的文字 */
    public static Optional<String> ocr(BufferedImage image) {
        return ocr(image, DEFAULT_LANGUAGE, DEFAULT_PAGE_SEG_MODE, null);
    }

    /**
     * 识别图片文件中的文字（完整参数）。
     *
     * @param image         图片文件
     * @param language      识别语言，如 eng、chi_sim
     * @param pageSegMode   页面分割模式（PSM），7 表示单行文本
     * @param charWhitelist 字符白名单，null 或空表示不限制
     * @return 识别结果；图片不存在或识别失败返回 {@link Optional#empty()}
     */
    public static Optional<String> ocr(File image, String language, int pageSegMode, String charWhitelist) {
        if (image == null || !image.isFile()) {
            log.warn("图片不存在或不是文件: {}", image);
            return Optional.empty();
        }
        try {
            return Optional.ofNullable(getTesseract(language, pageSegMode, charWhitelist, null).doOCR(image));
        } catch (TesseractException e) {
            log.error("OCR 识别失败: {}", image, e);
            return Optional.empty();
        }
    }

    /** 识别指定路径图片中的文字（完整参数） */
    public static Optional<String> ocr(String imagePath, String language, int pageSegMode, String charWhitelist) {
        return ocr(toFile(imagePath), language, pageSegMode, charWhitelist);
    }

    /** 识别图片中的文字（完整参数） */
    public static Optional<String> ocr(BufferedImage image, String language, int pageSegMode, String charWhitelist) {
        if (image == null) {
            return Optional.empty();
        }
        try {
            return Optional.ofNullable(getTesseract(language, pageSegMode, charWhitelist, null).doOCR(image));
        } catch (TesseractException e) {
            log.error("OCR 识别失败", e);
            return Optional.empty();
        }
    }

    /**
     * 识别「文档 / 代码截图」类图片（多行连续文本，含缩进）。
     *
     * <p>与 {@link #ocr(String)} 的区别：
     * <ul>
     *     <li>页面分割模式用 6（PSM_SINGLE_BLOCK，整块连续文本），而非自动版面分析</li>
     *     <li>先把图片放大 {@value #DOCUMENT_UPSCALE} 倍：截图上的小字放大后识别率显著提升</li>
     *     <li>开启 {@code preserve_interword_spaces}，保留词间空格与缩进</li>
     * </ul>
     * 返回结果会保留换行与空格。
     *
     * @param imagePath 图片路径
     * @return 识别结果；图片不存在或识别失败返回 {@link Optional#empty()}
     */
    public static Optional<String> ocrDocument(String imagePath) {
        return ocrDocument(toFile(imagePath));
    }

    /**
     * 识别「文档 / 代码截图」类图片文件，详见 {@link #ocrDocument(String)}。
     *
     * @param image 图片文件
     * @return 识别结果；图片不存在或无法解析返回 {@link Optional#empty()}
     */
    public static Optional<String> ocrDocument(File image) {
        if (image == null || !image.isFile()) {
            log.warn("图片不存在或不是文件: {}", image);
            return Optional.empty();
        }
        try {
            BufferedImage src = ImageIO.read(image);
            if (src == null) {
                log.warn("图片无法解析: {}", image);
                return Optional.empty();
            }
            return ocrDocument(src);
        } catch (IOException e) {
            log.error("读取图片失败: {}", image, e);
            return Optional.empty();
        }
    }

    /**
     * 识别「文档 / 代码截图」类图片，详见 {@link #ocrDocument(String)}。
     *
     * @param image 待识别图片
     * @return 识别结果；入参为空返回 {@link Optional#empty()}
     */
    public static Optional<String> ocrDocument(BufferedImage image) {
        if (image == null) {
            return Optional.empty();
        }
        try {
            Map<String, String> variables = Collections.singletonMap("preserve_interword_spaces", "1");
            ITesseract tesseract = getTesseract(DEFAULT_LANGUAGE, DOCUMENT_PAGE_SEG_MODE, null, variables);
            return Optional.ofNullable(tesseract.doOCR(scaleUp(image, DOCUMENT_UPSCALE, DOCUMENT_MAX_SIDE)));
        } catch (TesseractException e) {
            log.error("OCR 识别失败", e);
            return Optional.empty();
        }
    }

    /**
     * 等比放大图片。截图类图片字号偏小，放大后字符笔画更清晰，可明显提升识别率；
     * 注意不要转灰度——彩色语法高亮转灰度后会丢失区分度，反而降低识别率。
     *
     * @param src     原图
     * @param factor  期望倍数
     * @param maxSide 放大后允许的最大边长，超出时自动降低倍数
     */
    private static BufferedImage scaleUp(BufferedImage src, int factor, int maxSide) {
        int longest = Math.max(src.getWidth(), src.getHeight());
        int actual = Math.min(factor, Math.max(1, maxSide / Math.max(1, longest)));
        if (actual <= 1) {
            return src;
        }
        int width = src.getWidth() * actual;
        int height = src.getHeight() * actual;
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = out.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawImage(src, 0, 0, width, height, null);
        g.dispose();
        return out;
    }

    private static File toFile(String imagePath) {
        return imagePath == null ? null : new File(imagePath);
    }

    /** 按配置获取（并缓存）ITesseract 实例 */
    private static ITesseract getTesseract(String language, int pageSegMode, String charWhitelist,
                                           Map<String, String> extraVariables) {
        String key = language + "|" + pageSegMode + "|" + (charWhitelist == null ? "" : charWhitelist)
                + "|" + (extraVariables == null ? "" : new TreeMap<>(extraVariables));
        return CACHE.get().computeIfAbsent(key, k -> {
            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath(TESSDATA_PATH);
            tesseract.setLanguage(language);
            tesseract.setPageSegMode(pageSegMode);
            if (charWhitelist != null && !charWhitelist.isEmpty()) {
                tesseract.setVariable("tessedit_char_whitelist", charWhitelist);
            }
            if (extraVariables != null) {
                extraVariables.forEach(tesseract::setVariable);
            }
            tesseract.setVariable("user_defined_dpi", DEFAULT_DPI);
            return tesseract;
        });
    }
}
