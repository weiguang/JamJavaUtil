package com.okayjam.util;

import ai.onnxruntime.OrtException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

/**
 * OCR 相关测试：验证码识别（Tesseract / ddddocr 对比）与文档、代码截图的文本识别（Tesseract）。
 *
 * @author: Chen weiguang <chen2621978@gmail.com.com>
 * @create: 2018/11/26 11:34
 **/
public class OcrCodeUtilTest {

    /**
     * 对比 Tesseract 与 ddddocr 两种方案对「验证码」的识别效果。
     * 注意：ddddocr 需先准备 models/common_old.onnx 模型文件。
     */
    @Test
    public void compareVerifyCodeOcr() throws IOException, OrtException {
        String[] images = {"captcha.png", "captcha1.png"};
        for (String image : images) {
            String tesseract = VerifyCodeUtil.tesseractOcrCode(image).orElse("N/A");
            String ddddocr = VerifyCodeUtil.ddddOcrCode(image).orElse("N/A");
            String defaultResult = VerifyCodeUtil.OCRCode(image);
            System.out.printf("%s => tesseract=[%s], ddddocr=[%s], default(OCRCode)=[%s]%n",
                    image, tesseract, ddddocr, defaultResult);
        }
    }

    @Test
    public void ocrDocumentPlainText() {
        BufferedImage image = new BufferedImage(700, 140, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        try {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
            graphics.drawString("Hello World", 30, 50);
            graphics.drawString("Second Line", 100, 105);
        } finally {
            graphics.dispose();
        }

        Optional<String> result = TesseractOcrUtil.ocrDocument(image);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Hello World\nSecond Line", result.get().trim());
    }

    @Test
    public void ocrDocumentBlankImage() {
        BufferedImage image = new BufferedImage(200, 80, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        try {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        } finally {
            graphics.dispose();
        }

        Optional<String> result = TesseractOcrUtil.ocrDocument(image);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("", result.get());
    }

    /**
     * Recognizes the multi-line Java code screenshot in img.png.
     *
     * <p>Uses {@link TesseractOcrUtil#ocrDocument(java.io.File)} with PSM 6 and 3x scaling.
     */
    @Test
    public void ocrDocumentImage() {
        File image = new File("img.png");
        Assumptions.assumeTrue(image.isFile(), "img.png 不存在，跳过该用例");

        Optional<String> result = TesseractOcrUtil.ocrDocument(image);
        System.out.println("=== TesseractOcrUtil.ocrDocument(img.png) ===");
        System.out.println(result.orElse("(无结果)"));

        Assertions.assertTrue(result.isPresent(), "img.png 应能识别出文字");

        String text = result.get();
        String[] expectedSnippets = {"compareOcr", "DdddOcrUtil", "VerifyCodeUtil", "tesseractOcrCode"};
        for (String snippet : expectedSnippets) {
            Assertions.assertTrue(text.contains(snippet),
                    "识别结果应包含 [" + snippet + "]，实际识别结果:\n" + text);
        }
    }
}
