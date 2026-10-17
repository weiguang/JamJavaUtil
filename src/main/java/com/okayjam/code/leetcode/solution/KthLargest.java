package com.okayjam.code.leetcode.solution;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 703. 数据流中的第 K 大元素
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 *
 * @author JamChen jamchen@tencent.com
 * @date 2026/10/17 14:52
 **/
public class KthLargest {
    private final PriorityQueue<Integer> pq;
    private final int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.pq = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (pq.size() < k) {
            pq.offer(val);
        } else if (val > pq.peek()) {
            pq.poll();
            pq.offer(val);
        }
        return pq.peek();
    }

}
