package main.com.okayjam.job.newcoder.wanmeishijie;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/27 14:42.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    static int Min = 0;
    public static void main (String[] args) {
//        System.out.println("Hello world!");
        func2();
    }
    static void func1() {
        int n = sn.nextInt();
        int a[] = new int[n];
        int b[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sn.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = sn.nextInt();
        }
        Arrays.sort(a);
        Arrays.sort(b);
        int s = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int j = 0;
            for (j = s; j < n; j++) {
                if(a[j] > b[i]) {sum +=100; s = j+1; break;}
                else if(a[j] == b[i] && j==n-1) {break;}
            }
            if(j == n) sum-=100;
        }
        System.out.println(sum);
    }

    static void func2() {
        int n = sn.nextInt();
        int m = sn.nextInt();
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = sn.nextInt();
            }
        }
        find(a,0,Integer.MAX_VALUE,0,0);
        if(Min >0) Min =1;
        System.out.println(Math.abs(Min));
    }

    static void find(int[][] a, int cur, int curMin, int x, int y) {
        if( x >= a.length || y >= a[0].length) return;
        cur += a[x][y];
        if(cur < curMin) curMin = cur;
        if(x == a.length - 1 && y == a[0].length - 1) {
            if(Min == 0) Min = curMin;
            else if(curMin < Min) Min = curMin;
            return;
        }
        find(a,cur,curMin,x+1,y);
        find(a,cur,curMin,x,y+1);
    }

    public static  void test() {
        System.out.println(3*0.1==0.3);
    }
}
