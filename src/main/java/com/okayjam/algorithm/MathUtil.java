package com.okayjam.algorithm;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/19 17:10.
 */
public class MathUtil {
    public static boolean isPrime(long n) {
        if(n < 2) return false;
        if(n == 2) return true;
        if(n % 2 == 0) return false;
        for (int i = 3; i <= java.lang.Math.sqrt(n); i+=2) {
            if(n % i == 0) return false;
        }
        return true;
    }
}
