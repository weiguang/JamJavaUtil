package com.okayjam.util;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.Optional;
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
            return Optional.ofNullable(getTesseract(language, pageSegMode, charWhitelist).doOCR(image));
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
            return Optional.ofNullable(getTesseract(language, pageSegMode, charWhitelist).doOCR(image));
        } catch (TesseractException e) {
            log.error("OCR 识别失败", e);
            return Optional.empty();
        }
    }

    private static File toFile(String imagePath) {
        return imagePath == null ? null : new File(imagePath);
    }

    /** 按配置获取（并缓存）ITesseract 实例 */
    private static ITesseract getTesseract(String language, int pageSegMode, String charWhitelist) {
        String key = language + "|" + pageSegMode + "|" + (charWhitelist == null ? "" : charWhitelist);
        return CACHE.get().computeIfAbsent(key, k -> {
            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath(TESSDATA_PATH);
            tesseract.setLanguage(language);
            tesseract.setPageSegMode(pageSegMode);
            if (charWhitelist != null && !charWhitelist.isEmpty()) {
                tesseract.setTessVariable("tessedit_char_whitelist", charWhitelist);
            }
            tesseract.setTessVariable("user_defined_dpi", DEFAULT_DPI);
            return tesseract;
        });
    }
}
