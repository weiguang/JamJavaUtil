package com.okayjam.job.newcoder.toutiao;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/10/17 19:33.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main (String[] args) {
       func2();
    }
    static void func2() {
        int n = sn.nextInt();
        int x = sn.nextInt();
        int[] a = new int[n];
        int min = 0;
        for (int i = 0; i < n; i++) {
            a[i] = sn.nextInt();
            if(a[i] < a[min]) min = i;
        }
        int m = a[min];
        for (int i = 0; i < n; i++) {
            a[i] -= m;
        }
//        int i = 0;
//        for ( i = n-1; i >= x; i--) {
//            if(a[i] == 0) break;
//            else a[i]--;
//        }
//
//        a[i] =  m * n;
//        if(x-1!=i) a[i] += n-i+x-1;

        int sum = m * n;
        int i = 0;
        for ( i = x - 1;  ; i--) {
            if(i < 0) i= n - 1;
            if(a[i] !=0 ) {sum++; a[i]--;}
            else break;
        }
        a[i]+=sum;

        for (int j = 0; j < n-1; j++) {
            //if(j<x) a[j]--;
            System.out.print(a[j] + " ");
        }
        if(n>0) System.out.println(a[n-1]);
    }
}
