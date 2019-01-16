package com.test;

/**
 * @author: Chen weiguang <weiguangchen@sf-express.com>
 * @create: 2018/12/04 10:15
 **/
public class TestStatic1 {
    static {
        System.out.println("test1 static block!");
    }

    public static void main(String[] args) {
        System.out.println("Default main method!");

        System.out.println("---------------------------------");
        TestStatic2.staticMenthod();
    }
}
