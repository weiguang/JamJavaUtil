package com.okayjam.test;

/**
 * @author: Chen weiguang <weiguangchen@sf-express.com>
 * @create: 2018/12/04 10:15
 **/
public class TestStatic2 {
    static String testValue = "Test value!";

    static {
        System.out.println("test2 static block!" + testValue);
    }



    public static void staticMenthod() {
        System.out.println("test2 static menthod!");
    }

}
