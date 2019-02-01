package com.okayjam.job.acmcoder.sf;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/20 14:53.
 */
public class Main {
    static Scanner cin = new Scanner(System.in);

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int[] res;
//
//        int _a_size = 0;
//        _a_size = Integer.parseInt(in.nextLine().trim());
//        int[] _a = new int[_a_size];
//        int _a_item;
//        for(int _a_i = 0; _a_i < _a_size; _a_i++) {
//            _a_item = Integer.parseInt(in.nextLine().trim());
//            _a[_a_i] = _a_item;
//        }
//
//        res = solve(_a);
//        for(int res_i=0; res_i < res.length; res_i++) {
//            System.out.println(String.valueOf(res[res_i]));
//        }
        func1();

    }

    static int[] solve(int[] a) {
        int[] ans = new int[a.length];
        for (int i = a.length - 1; i >= 0; i--) {
            ans[i] = a[i];
            int f = 1;
            for (int j = i + 1; j < a.length; j++) {
                if (f == 1) {
                    ans[i] += ans[j];
                    f = 0;
                } else {
                    ans[i] -= ans[j];
                    f = 1;
                }
            }
        }
        return ans;
    }

    /**
     * 4和7是两个幸运数字，我们定义，十进制表示中，每一位只有4和7两个数的正整数都是幸运数字。
     * 求少于 n 的幸运数的个数
     */
    static void func1() {
        int n = cin.nextInt();
        String str = n + "";
        int MAX = (int) Math.pow(10, 9) + 7;
        int len = str.length();
        int sum = 0;
        if (str.charAt(0) >= '7')
            sum = (2 << (len - 1)) % MAX;
        else if (str.charAt(0) - '0' >= 4)
            sum = (2 << (len - 2)) % MAX;
        for (int i = 1; i != len; i++)
            sum = (sum + (2 << (i - 1))) % MAX;
        System.out.println(sum);

    }

    static int getmn(int m, int n) {
        int i, j, k, x, y;
        n = cin.nextInt();
        k = 0;
        for (j = m; j <= n; j++) {
            y = j;
            x = 0;
            while (y % 10 == 4 || y % 10 == 7)//这里是关键，用一般的循环会导致时间超时，这也是我之前做时的问题所在
            {
                x++;
                y = y / 10;
            }
            if (y / Math.pow(10, x) == 0)
                k++;
        }
        return k;
    }

}
