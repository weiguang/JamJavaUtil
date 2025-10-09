package com.okayjam.code.leetcode;

/**
 * 304. 二维区域和检索 - 矩阵不可变
 * <a href="https://leetcode.cn/problems/range-sum-query-2d-immutable">304. 二维区域和检索 - 矩阵不可变</a>
 *
 * @author JamChen jamchen@tencent.com
 * @date 2025/10/09 10:05
 **/
public class NumMatrix {

    private final int[][] sums;

    public NumMatrix(int[][] matrix) {
        sums = new int[matrix.length][matrix[0].length];
        sums[0][0] = matrix[0][0];
        for(int i = 1; i < matrix.length; i++) {
            sums[i][0] =  sums[i -1][0] +  matrix[i][0];
        }
        for(int j = 1; j < matrix[0].length; j++) {
            sums[0][j] =  sums[0][j-1] +  matrix[0][j];
        }
        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[0].length; j++) {
                sums[i][j] += matrix[i][j] - sums[i-1][j-1] +  sums[i][j-1] + sums[i-1][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int left = col1 == 0 ? 0 :sums[row2 ][col1 - 1];
        int top =  row1 == 0 ? 0 :sums[row1 - 1][col2];
        int leftTop = row1 !=0 && col1 != 0 ? sums[row1-1][col1-1] : 0;
        return sums[row2][col2] - left - top + leftTop;
    }

}
