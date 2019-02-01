package com.okayjam.job.newcoder.nvidia;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/14 14:44.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main(String args[]) {
        while (sn.hasNextLine()) {
            String s = sn.nextLine();
            func1(s);
        }

    }

    public static void func1(String s) {
        s = s.replaceAll("&lt;","<");
        s = s.replaceAll("&gt;",">");
        s = s.replaceAll("&amp;","&");
        s= s.replaceAll("&quot;","\"");
        s= s.replaceAll("&nbsp;"," ");
        System.out.println(s);
    }
}
