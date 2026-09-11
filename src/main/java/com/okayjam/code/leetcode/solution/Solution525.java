package com.okayjam.code.leetcode.solution;

import java.util.Random;

/**
 * com.okayjam.code.leetcode.solution
 *
 * @author JamChen jamchen@tencent.com
 * @date 2026/08/20 09:33
 **/
public class Solution525 {
    private int[] sum;
    private Random random;
    public Solution525(int[] w) {
        random = new Random();
        sum = new int[w.length];
        sum[0] = w[0];
        for (int i = 1; i <w.length; i++) {
            sum[i] = sum[i -1] + w[i];
        }
    }

    public int pickIndex() {
        int ran = random.nextInt(sum[sum.length - 1]) + 1;
        int l =0, r = sum.length -1;
        while (l < r) {
            int m = (r - l) / 2 + l;
            if (sum[m] >= ran)  r = m;
            else l = m + 1 ;
        }
        return l;
    }
}

