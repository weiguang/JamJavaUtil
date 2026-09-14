package com.okayjam.test;

/**
 * @author: Chen weiguang <chen2621978@gmail.com>
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
