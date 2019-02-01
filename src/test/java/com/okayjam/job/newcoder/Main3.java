package com.okayjam.job.newcoder;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/1 21:22.
 */
public class Main3 {
    static Scanner sn = new Scanner(System.in);
    public static void main(String[] args) {
//        sentenceReveser();
//        narcissisticNumber();
        sumSqrt();
    }

    public static void sentenceReveser() {
        String s = sn.nextLine();
        String sa[] = s.split(" ");
        for (int i = sa.length-1; i > 0; i--) {
            System.out.print(sa[i] + " ");
        }
        System.out.println(sa[0]);
    }
    static void narcissisticNumber() {
        int m = sn.nextInt();
        int n = sn.nextInt();
        int ans = 0;
        String s = "";
        for (int i = m; i <= n; i++) {
            if(isNarcissisticNumber(i)) {ans++;
                s += i + " ";}
        }
        if(ans == 0) System.out.println("no");
        else System.out.println(s.substring(0,s.length() -1));
    }

    public static boolean isNarcissisticNumber(int n){
        if(n<100 || n>999) return false;
        int a = n % 10, b = n / 10 % 10, c = n / 100;
        if( a*a*a + b*b*b + c*c*c == n) return true;
        return false;
    }

    static void sumSqrt() {
        int n = sn.nextInt();
        int m = sn.nextInt();
        double ans = n;
        double t = n;
        for (int i = 1; i < m; i++) {
            ans += (t = Math.sqrt(t));
        }
        System.out.printf("%.2f", ans);
    }
}