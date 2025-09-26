package com.okayjam.code.leetcode;

/**
 *251. 展开二维向量
 * <a href="https://leetcode.cn/problems/flatten-2d-vector/">251. 展开二维向量</a>
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2025/09/26 22:13
 **/
public class Vector2D {
    int i = 0, j = 0;
    int[][] vec;

    public Vector2D(int[][] vec) {
        this.vec = vec;
        while (i < vec.length && j == vec[i].length) {
            j = 0;
            i++;
        }
    }

    public int next() {
        int cur = vec[i][j++];
        while (i < vec.length && j == vec[i].length) {
            j = 0;
            i++;
        }
        return cur;
    }

    public boolean hasNext() {
//        return cur < total;
        return i < vec.length && j < vec[i].length;
    }


}
