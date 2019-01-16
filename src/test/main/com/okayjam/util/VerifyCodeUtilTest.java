package com.okayjam.util;


import com.util.VerifyCodeUtil;
import org.junit.jupiter.api.Test;

/**
 * @author: Chen weiguang <weiguangchen@sf-express.com>
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
}