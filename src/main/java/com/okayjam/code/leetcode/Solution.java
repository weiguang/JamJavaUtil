package com.okayjam.code.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * com.okayjam.code.leetcode
 *
 * @author JamChen jamchen@tencent.com
 * @date 2026/07/02 09:00
 **/
public class Solution {
    private final int[] nums;

    public Solution(int[] nums) {
        this.nums = nums;
    }

    public int[] reset() {
        return nums;
     }

    public int[] shuffle() {
        int[] shuffled = nums.clone();
        Random random = new Random();
        for (int i = 1; i < nums.length; i++) {
            int i1 = random.nextInt(i +1);
             int t =  shuffled[i1];
             shuffled[i1] = shuffled[i];
             shuffled[i] = t;
        }
        return shuffled;
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new int[] {1, 2, 3});
        System.out.println(Arrays.toString(solution.shuffle()));
        System.out.println(Arrays.toString(solution.reset()));
        System.out.println(Arrays.toString(solution.shuffle()));
    }

//
//    public NestedInteger deserialize(String s) {
//        if (s == null || s.isEmpty()) { return null; }
//        // 如果不是以 '[' 开头，说明纯粹是一个数字
//        if (!s.startsWith("[")) {
//            return new NestedInteger(Integer.parseInt(s));
//        }
//        NestedInteger ans = new NestedInteger();
//        deserialize(s, 1, ans);
//        return ans;
//    }
//
//    public int deserialize(String s, int start, NestedInteger cur) {
//        if (s.length() <= start) return start;
//        int i = start;
//        StringBuilder sb = new StringBuilder();
//        while (i < s.length()) {
//            char c = s.charAt(i);
//            if (c == '[') {
//                NestedInteger next = new NestedInteger();
//                i = deserialize(s, i + 1, next);
//                cur.add(next);
//            } else if (c == ']' || c == ',') {
//                if ( sb.length() > 0 ) {
//                    NestedInteger next = new NestedInteger();
//                    next.setInteger(Integer.parseInt(sb.toString()));
//                    cur.add(next);
//                    sb = new StringBuilder();
//                }
//                // 如果是 ']'，说明当前层级结束了，返回给上层
//                if (c == ']') {
//                    return i;
//                }
//            }  else  {
//                sb.append(c);
//            }
//            i++;
//        }
//        return i;
//    }


    interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();

    }

}


