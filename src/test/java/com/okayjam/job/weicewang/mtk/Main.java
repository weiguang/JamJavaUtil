package com.okayjam.job.weicewang.mtk;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/7 18:45.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    static int min = Integer.MIN_VALUE;
    public static void main(String args[]) {
//        int H = sn.nextInt();
//        int x= sn.nextInt();
//        int y = sn.nextInt();
//        int h = sn.nextInt();
//        int s = sn.nextInt();
//        func1(H,x,y,h,s);
        while(sn.hasNext()) {
            int n = sn.nextInt();
            int m = sn.nextInt();
            int x1 = sn.nextInt();
            int y1 = sn.nextInt();
            int x2 = sn.nextInt();
            int y2 = sn.nextInt();
            int[][] data =  new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    data[i][j] = sn.nextInt();
                }
            }
            int len = 0;
            min = m * n;
//            if(x1==x2 && y1 == y2) {
//                System.out.println(0);
//                continue;
//            }
            if(!checkBound(n,m ,x2,y2) || !checkBound(n,m ,x1,y1) || !checkRadiate(n,m,x2,y2,data)) System.out.println(-1);
            else {
                func2(n, m, x1, y1, x2, y2,data,len);
                System.out.println(min >= m*n? -1: min);
            }

        }
    }

    public static void func1(int H, int x, int y, int h, int s){
       double k = 1.0 * h / x;
       long ans = 0;
       double remain = s - k;
       if (k > s) ans = x*s;
       else {
           if(x <= y) ans = h;
           else {
               double t = (x - y) * remain + h;
               ans = Math.round(t > H ? H : t);
           }
       }
        System.out.println(ans);
    }

    public static void func2(int n, int m, int x1,int y1, int x2, int y2, int[][] data, int len) {
        if(len > min) return;
        if(!checkBound(n,m,x1,y1)) return;
        if(data[x1][y1] != 0) return;
        if(!checkRadiate(n,m,x1,y1,data)) return;
        if(x1 == x2 && y1 == y2) {
            if(len < min) min = len;
        } else {
            func2(n,m,x1 + 1,y1, x2, y2,data,len + 1);
            func2(n,m, x1,y1 + 1, x2, y2,data,len + 1);
            func2(n,m,x1 - 1,y1 , x2, y2,data,len + 1);
            func2(n,m, x1,y1 - 1, x2, y2,data,len + 1);
        }
    }
    public static boolean checkBound(int n, int m, int x, int y) {
        if(x < 0 || x >= n) return false;
        if(y < 0 || y >= m) return false;
        return true;
    }

    public static boolean checkRadiate(int n,int m,int x1,int y1,int[][]data) {
        for (int i = 1; i < n - 1; i++) {
            if(checkBound(n,m,x1 + i,y1) && data[x1+i][y1] >= i + 1) return false;
//            if(checkBound(n,m,x1 + 2,y1) && data[x1+2][y1] >= 3) return false;
            if(checkBound(n,m,x1 - i,y1) && data[x1-i][y1] >= i) return false;
//            if(checkBound(n,m,x1 - 2,y1) && data[x1-2][y1] >= 3) return false;
        }

        for (int i = 1; i < m - 1; i++) {
            if(checkBound(n,m,x1 ,y1 + i) && data[x1][y1 + i] >= i+1) return false;
//            if(checkBound(n,m,x1 ,y1 + 2) && data[x1][y1 + 2] >= 3) return false;
            if(checkBound(n,m,x1 ,y1 - i) && data[x1][y1 - i] >= i + 1) return false;
//            if(checkBound(n,m,x1 ,y1 - 2) && data[x1][y1 - 2] >= 3) return false;
        }

        return true;
    }
}
