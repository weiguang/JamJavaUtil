package com.okayjam.util;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * 验证码图片预处理工具。
 */
public class ImagePreProcess2 {

    /**
     * 判断像素是否接近白色：R + G + B >= 200 视为白。
     *
     * @param colorInt 像素 RGB 值
     * @return 1 表示白色，0 表示非白
     */
    public static int isWhite(int colorInt) {
        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() >= 200) {
            return 1;
        }
        return 0;
    }

    /**
     * 将图片二值化：接近白色的像素置为纯白，其余置为纯黑。
     * 注意：会原地修改传入的图片，并返回同一对象。
     *
     * @param img 待处理的图片
     * @return 处理后的图片
     */
    public static BufferedImage removeBackgroud2(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                if (isWhite(img.getRGB(x, y)) == 1) {
                    img.setRGB(x, y, Color.WHITE.getRGB());
                } else {
                    img.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
        return img;
    }
}
