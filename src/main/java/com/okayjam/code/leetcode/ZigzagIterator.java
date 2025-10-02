package com.okayjam.code.leetcode;

import java.util.List;

/**
 * 281. 锯齿迭代器
 * <a href="https://leetcode.cn/problems/zigzag-iterator/">281. 锯齿迭代器</a>
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2025/10/02 22:25
 **/
public class ZigzagIterator {

    List<Integer> v1 ;
    List<Integer> v2  ;
    int cur1 = 0, cur2 = 0;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.v1 = v1;
       this.v2 = v2;
    }

    public int next() {
        if (!hasNext()) {
           throw new RuntimeException();
        }
        // 取的情况v1
        if( (cur1 <= cur2 || cur2 >= v2.size()) && cur1 < v1.size()) {
            return v1.get(cur1++);
        }
         return v2.get(cur2++);
    }

    public boolean hasNext() {
        return cur1 < v1.size()  || cur2 < v2.size();
    }

}
