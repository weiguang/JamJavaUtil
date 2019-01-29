package com.okayjam.util;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File imageFile = new File("captcha.png");
        Tesseract instance = new Tesseract(); //
        instance.setDatapath(".\\tessdata");
        instance.setLanguage("eng");
        try {

            String result = instance.doOCR(imageFile);
            System.out.println(result);

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}