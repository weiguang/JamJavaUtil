package com.util;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author: Chen weiguang <weiguangchen@sf-express.com>
 * @create: 2018/11/22 16:28
 **/
public class VerifyCodeUtil {
    /**
     *
     * language pakeget:
     *  english: https://raw.githubusercontent.com/tesseract-ocr/tessdata/master/eng.traineddata
     *  all : https://codeload.github.com/tesseract-ocr/tessdata/zip/4.0.0
     * @param picName picture path
     * @return OCR result
     * @throws Exception
     */
    public static String OCRCode(String picName) throws Exception {
        File filepic = new File(picName);
        if(!filepic.exists()) {
            return null;
        }
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(".\\tessdata");
        tesseract.setLanguage("eng");
        try {
            String result = tesseract.doOCR(filepic);
            return result;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
        return null;
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
        if (driverPath == null) {
            System.setProperty("webdriver.chrome.driver",  driverPath);
        }
        WebDriver driver = new ChromeDriver();
        if (null != url) {
            driver.get(url);
        }
        return driver;
    }
}
