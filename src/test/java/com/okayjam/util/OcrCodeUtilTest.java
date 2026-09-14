package com.okayjam.util;

import ai.onnxruntime.OrtException;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

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

    /**
     * 文档 / 代码截图识别（Tesseract）：img.png 是一张多行 Java 代码截图。
     *
     * <p>使用 {@link TesseractOcrUtil#ocrDocument(java.io.File)} 识别
     * （PSM6 整块文本 + 放大 3 倍 + 保留缩进），并校验其中的关键代码片段。
     */
    @Test
    public void ocrDocumentImage() {
        File image = new File("img.png");
        Assume.assumeTrue("img.png 不存在，跳过该用例", image.isFile());

        Optional<String> result = TesseractOcrUtil.ocrDocument(image);
        System.out.println("=== TesseractOcrUtil.ocrDocument(img.png) ===");
        System.out.println(result.orElse("(无结果)"));

        Assert.assertTrue("img.png 应能识别出文字", result.isPresent());

        String text = result.get();
        String[] expectedSnippets = {"compareOcr", "DdddOcrUtil", "VerifyCodeUtil", "tesseractOcrCode"};
        for (String snippet : expectedSnippets) {
            Assert.assertTrue("识别结果应包含 [" + snippet + "]，实际识别结果:\n" + text,
                    text.contains(snippet));
        }
    }
}
