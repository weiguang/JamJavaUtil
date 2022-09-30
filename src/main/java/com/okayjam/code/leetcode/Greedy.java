package com.okayjam.code.leetcode;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/09/19 19:05
 **/
public class Greedy {
	/**
	 * 55. 跳跃游戏
	 * https://leetcode.cn/problems/jump-game/
	 * @param nums
	 * @return
	 */
	public boolean canJump(int[] nums) {
		int reach = 1;
		for (int i = 0; i < nums.length &&  i < reach ; i++) {
			reach = Math.max(reach, i+1+nums[i]);
			System.out.println(i +", " + reach);
		}
		return reach >= nums.length;
	}
}
