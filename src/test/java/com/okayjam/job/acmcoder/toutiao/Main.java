package com.okayjam.job.acmcoder.toutiao;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/22 16:06.
 */
public class Main {
    static Scanner cin = new Scanner(System.in);
    public static void main(String args[]) {
//        func1();
        new Main().func2();
    }

    public static void func1(){
        int n;
       // while (cin.hasNextInt()) {
            n = cin.nextInt();
            int[] x = new int[n];
            int[] y = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = cin.nextInt();
                y[i] = cin.nextInt();
            }
            for (int i = 0; i < n; i++) {
                int j = 0;
                for (j = 0; j < n; j++) {
                    if(i != j) {
                        if(x[j] >= x[i] && y[j] >= y[i]) break;
                    }
                }
                if(j == n) System.out.println(x[i] +" "+y[i]);
            }
        }
   // }

    public  void func2(){
        int N, M, P;
        N = cin.nextInt();
        M = cin.nextInt();
        P = cin.nextInt();
        for (int i = 0; i < P; i++) {
            Idea idea = new Idea();
            idea.num = cin.nextInt();
            idea.sTime = cin.nextInt();
            idea.pri = cin.nextInt();
            idea.uTime = cin.nextInt();
        }

    }

    class Idea implements Comparable{
        int num = 0;
        int sTime = 0;
        int pri = 0;
        int uTime = 0;

        @Override
        public int compareTo(Object o) {
            Idea idea = (Idea)o;
            int f = -(this.pri - idea.pri);
            if(f!=0) {
                f = this.uTime - idea.uTime;
                if(f!=0) {
                    f = this.sTime - idea.sTime;
                    if(f!=0) return this.num - this.num;
                }
            }
            return f;
        }
    }

}
