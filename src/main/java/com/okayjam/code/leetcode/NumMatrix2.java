package com.okayjam.code.leetcode;

/**
 *
 *
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2025/10/13 10:12
 **/
public class NumMatrix2 {
    int[][] matrix;
    int[][] rowSum;
    int m, n;

    public NumMatrix2(int[][] matrix) {
        this.matrix = matrix;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        m = matrix.length;
        n = matrix[0].length;
        rowSum = new int[m][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                rowSum[i][j] = rowSum[i][j - 1] + matrix[i][j - 1];
            }
        }
    }

    public void update(int row, int col, int val) {
        int detal = val - matrix[row][col];
        matrix[row][col] = val;
        for (int j = col + 1; j <= n; j++) {
            rowSum[row][j] += detal;
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            sum = sum + rowSum[i][col2 + 1] - rowSum[i][col1];
        }
        return sum;
    }
}
