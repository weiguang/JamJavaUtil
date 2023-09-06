package com.okayjam.code.leetcode;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/09/19 19:05
 **/
public class Greedy {

	/**
	 * 121. 买卖股票的最佳时机
	 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {
		if(prices.length <2) {return 0;}
		int curMin = prices[0];
		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			profit = Math.max(profit, prices[i] - curMin);
			curMin = Math.min(prices[i], curMin);
		}
		return profit;
	}

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
