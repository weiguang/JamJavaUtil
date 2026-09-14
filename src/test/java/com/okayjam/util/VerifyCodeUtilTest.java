package com.okayjam.util;


import org.junit.Test;

/**
 * @author: Chen weiguang <chen2621978@gmail.com>
 * @create: 2018/11/26 11:34
 **/
public class VerifyCodeUtilTest {

    @Test
    public void OCRCode() throws Exception {
       String  str =  VerifyCodeUtil.OCRCode("captcha.png");
        System.out.println(str);
        str =  VerifyCodeUtil.OCRCode("captcha1.png");
        System.out.println(str);
    }

    /**
     * 对比 Tesseract 与 ddddocr 两种方案的识别效果。
     * 注意：ddddocr 需先准备 models/common_old.onnx 模型文件。
     */
    @Test
    public void compareOcr() {
        String[] images = {"captcha.png", "captcha1.png"};
        for (String image : images) {
            String tesseract = VerifyCodeUtil.OCRCode(image);
            String ddddocr = VerifyCodeUtil.ddddOcrCode(image).orElse("N/A");
            System.out.printf("%s => tesseract=[%s], ddddocr=[%s]%n", image, tesseract, ddddocr);
        }
    }
}