package com.okayjam.util;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;

/**
 * 验证码识别工具：负责验证码图片的获取与识别。
 *
 * <p>文字识别能力由通用 OCR 工具类提供，本类只做验证码场景的封装：
 * <ul>
 *     <li>{@link TesseractOcrUtil}：Tesseract 方案</li>
 *     <li>{@link DdddOcrUtil}：ddddocr(ONNX) 方案</li>
 * </ul>
 * 如需识别其他类型的文字，直接使用上述通用工具类即可。
 *
 * @author: Chen weiguang <weiguangchen@sf-express.com>
 * @create: 2018/11/22 16:28
 **/
public class VerifyCodeUtil {

    private static final Logger log = LoggerFactory.getLogger(VerifyCodeUtil.class);

    /** 验证码字符集白名单，限定范围可显著减少误识别 */
    private static final String CHAR_WHITELIST =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /** 验证码通常为单行字符，PSM 7 = SINGLE_LINE */
    private static final int CAPTCHA_PAGE_SEG_MODE = 7;

    /** 验证码默认使用英文模型 */
    private static final String CAPTCHA_LANGUAGE = "eng";

    /**
     * 使用 Tesseract 识别验证码。
     *
     * <p>相比通用识别，这里启用了单行模式与字符白名单；识别失败返回 {@link Optional#empty()}。
     *
     * @param imagePath 图片路径
     * @return 识别结果（已去除空白字符）
     */
    public static Optional<String> ocrCode(String imagePath) {
        return TesseractOcrUtil.ocr(imagePath, CAPTCHA_LANGUAGE, CAPTCHA_PAGE_SEG_MODE, CHAR_WHITELIST)
                .map(s -> s.replaceAll("\\s+", ""));
    }

    /**
     * 兼容旧调用方式，识别失败时返回 {@code null}。
     *
     * @param imagePath 图片路径
     * @return 识别结果，失败为 {@code null}
     */
    public static String OCRCode(String imagePath) {
        return ocrCode(imagePath).orElse(null);
    }

    /**
     * 使用 ddddocr(ONNX) 识别验证码，与 {@link #ocrCode(String)} 的 Tesseract 方案并存，便于对比效果。
     *
     * <p>使用前请确认模型文件与 charset 已就绪，详见 {@link DdddOcrUtil}。
     *
     * @param imagePath 图片路径
     * @return 识别结果；失败返回 {@link Optional#empty()}
     */
    public static Optional<String> ddddOcrCode(String imagePath) {
        try {
            return DdddOcrUtil.ocr(imagePath);
        } catch (Exception e) {
            log.error("ddddocr 识别失败: {}", imagePath, e);
            return Optional.empty();
        }
    }

    /**
     * 获取页面中的验证码
     * @param driver
     * @param ele
     * @param savePath
     * @throws Exception
     */
    static void getCodeToLocation(WebDriver driver,WebElement ele, String savePath) throws Exception
    {
       // driver.get(loginUrl);
//        WebElement ele = driver.findElement(By.className("yzmImg"));
        // Get entire page screenshot
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = ImageIO.read(screenshot);

        // Get the location of element on the page
        Point point = ele.getLocation();

        // Get width and height of the element
        int eleWidth = ele.getSize().getWidth();
        int eleHeight = ele.getSize().getHeight();

        // Crop the entire page screenshot to get only element screenshot
        BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
                eleWidth, eleHeight);
        eleScreenshot=ImagePreProcess2.removeBackgroud2(eleScreenshot);
        ImageIO.write(eleScreenshot, "png", new File(savePath));

    }

    /**
     * 获取WebDriver
     * @param url
     * @return
     */
    static WebDriver getWebDriver(String url) {
        return getWebDriver(url,null);
    }

    /**
     * 获取WebDriver
     * @param url
     * @return
     */
    static WebDriver getWebDriver(String url, String driverPath) {
        if (driverPath != null) {
            System.setProperty("webdriver.chrome.driver", driverPath);
        }
        WebDriver driver = new ChromeDriver();
        if (null != url) {
            driver.get(url);
        }
        return driver;
    }
}
