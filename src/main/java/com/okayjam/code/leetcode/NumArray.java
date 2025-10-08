package com.okayjam.code.leetcode;

/**
 *
 *
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2025/10/08 20:39
 **/
public class NumArray {
    private final int[] sum;

    public NumArray(int[] nums) {
        sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return sum[right + 1] - sum[left];
    }
}
