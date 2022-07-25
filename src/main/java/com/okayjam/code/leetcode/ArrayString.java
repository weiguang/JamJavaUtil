package com.okayjam.code.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/07/03 10:18
 **/
public class ArrayString {
    public static void main(String[] args) {
        System.out.println("Default main method!");
//        int[] nums = new int[]{100,4,200,1,3,2};
//        System.out.println(new ArrayString().longestConsecutive(nums));
//        System.out.println(Arrays.toString(new ArrayString().twoSum(new int[]{100, 4, 200, 1, 3, 2}, 9)));
        System.out.println(Arrays.toString(new ArrayString().plusOne(new int[]{9})));
    }

    public int[] plusOne(int[] digits) {
        int flag = 1;
        for (int i = digits.length - 1; i >=0 ; i--) {
            if (flag == 0) {
                break;
            }
            int t = (digits[i] + flag);
            flag = t / 10;
            digits[i] = t % 10;
        }
        int[] re;
        if (flag == 0 ) {
            re =digits;
        } else {
            re  = Arrays.copyOf(digits, digits.length + 1);
            re[0] = flag;
        }
        return re;
    }

    /**
     * 旋转图像
     * https://leetcode.cn/problems/rotate-image/
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n -1 -i][j];
                matrix[n-1-i][j] = temp;
            }
        }
        // 对角线
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * 42. 接雨水
     * https://leetcode.cn/problems/trapping-rain-water/
     * @param height
     * @return
     */
    public int trap(int[] height) {
        LinkedList<Integer> stack = new LinkedList<>();
        int  re = 0;
        int max = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] > height[max]) {
                max = i;
            }
        }
        for (int i = 0, left = 0; i < max; i++) {
            if (height[i] >= left) {left = height[i];}
            else {
                re += left - height[i];
            }
        }
        for (int j = height.length -1, right = 0; j > max ; j--) {
            if (height[j] > right) {right = height[j];}
            else {
                re += right - height[j];
            }
        }
        return  re;
    }
    public int trap2(int[] height) {
        LinkedList<Integer> stack = new LinkedList<>();
        int  re = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currW = i - left - 1;
                int curH = Math.min(height[left], height[i]) - height[top];
                re += currW * curH;
            }
            stack.push(i);
        }
            return  re;
    }


    /**
     * 36. 有效的数独
     * https://leetcode.cn/problems/valid-sudoku/
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][][] box = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int idx = c - '0' - 1;
                    rows[i][idx]++;
                    cols[j][idx]++;
                    box[i/3][j/3][idx]++;
                    if ( rows[i][idx] > 1 ||  cols[j][idx]> 1 || box[i/3][j/3][idx] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public void nextPermutation(int[] nums) {
        int i =  nums.length - 2;
        for ( ; i >=0 && nums[i] >= nums[i+1] ; i--) {}
        if (i >= 0 ) {
            int j = nums.length - 1;
            for (; j >=0 && nums[i] >= nums[j] ; j--) {}
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] re = new int[2];
        Map<Integer, Integer> map = new HashMap<>((int) (nums.length / 0.75 + 1));
        for (int i = 0; i < nums.length; i++) {
            Integer t = map.get(target - nums[i]);
            if (t != null) {
                re[0] = t;
                re[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return re;
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> map = new HashSet<>((int) (nums.length / 0.75 + 1));
        for (int i = 0; i < nums.length; i++) {
            map.add(nums[i]);
        }
        int re = 0;
        for (int num : nums) {
            if (map.contains( num - 1)) {
                continue;
            }
            int tmax = 1;
            int cur = num;
            while (map.contains(++cur)) {
                tmax ++;
            }
            re = Math.max(re, tmax);
        }
        return re;
    }


    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] re  = new int[n*m];
        int index = 0;
        for (int i = 0; i < m ; i++) {
            for (int j = 0; j <= i; j++) {
            if ((i&1) == 1) {
                re[index++] = mat[j][i-j];
            } else {
                re[index++] = mat[i-j][j];
                }
            }
        }
        for (int i = m-2; i >= 0 ; i--) {
            for (int j = 0; j <= i; j++) {
                if ((i&1) == 1) {
                    re[index++] = mat[m-1-i+j][n-j-1];

                } else {
                    re[index++] = mat[n-1-j][m-j-1];
                }
            }
        }
        return re;
    }
}
