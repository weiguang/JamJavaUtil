package com.okayjam.designpattern.decorator;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2019/10/16 11:17
 **/
public class TestDemo {
    public static void main(String[] args) throws IOException {
        InputStream in = new LowerCaseInputStream(new BufferedInputStream(System.in));
        int c;
        byte b [] = new byte[100];
        while ((c = in.read(b,0,100)) > 0) {
            System.out.print(new String(b,0, c, StandardCharsets.UTF_8));
        }
        in.close();
    }
}
