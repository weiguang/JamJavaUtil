package com.okayjam.job.acmcoder.yixin;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/28 19:56.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main (String[] args) {
      func2();
    }
     static void func1() {
         String s = sn.next();
         String t = sn.next();
         int re = s.indexOf(t);
         System.out.println(re);
     }

     static  void func2() {
         String n = sn.next();
         String w = sn.next();
         BigInteger bn = new BigInteger(n+"");
         BigInteger bw = new BigInteger(w  + "");
         BigDecimal ba = new BigDecimal(n);
         int re = bn.pow(bw.intValue()).subtract(
                 bn.multiply(
                         bn.subtract(new BigInteger("1")))
                         .pow(bw.subtract(new BigInteger("1")).intValue()))
                 .mod(new BigInteger("100003"))
                 .intValue();
         System.out.println(re);
//         long re = n-1;
//         for (int i = 1; i < w; i++) {
//             re = re * n;
//         }
        // System.out.println((int)((Math.pow(n,w)-n*Math.pow(n-1,w-1)) % 100003));
     }
}
