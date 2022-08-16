package com.okayjam.code.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/08/16 19:23
 **/
public class BruteForceAttack {
	/**
	 * 78. 子集
	 * https://leetcode.cn/problems/subsets/
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsets2(int[] nums) {
		List<List<Integer>> re = new ArrayList<>();
		List<Integer> t = new ArrayList<Integer>();
		dfs(re, t, 0, nums);
		return re;
	}
	public void dfs(List<List<Integer>> re, List<Integer> t, int cur, int[] nums) {
		if (cur == nums.length) {
			re.add(new ArrayList<>(t));
			return;
		}
		t.add(nums[cur]);
		dfs(re, t, cur+1, nums);
		t.remove(t.size() - 1);
		dfs(re, t, cur+1, nums);
	}

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> re = new ArrayList<>();
		for (int mask = 0; mask < (1 << nums.length); mask++) {
			List<Integer> t = new ArrayList<>();
			for (int j = 0; j < nums.length; j++) {
				if ((mask & (1<<j)) != 0) {
					t.add(nums[j]);
				}
			}
			re.add(t);
		}
		return re;
	}

	/**
	 * 90. 子集 II
	 * https://leetcode.cn/problems/subsets-ii/
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> re = new ArrayList<>();
		Arrays.sort(nums);
		for (int mask = 0; mask < (1 << nums.length); mask++) {
			List<Integer> t = new ArrayList<>();
			boolean flag = true;
			for (int j = 0; j < nums.length; j++) {
				if ((mask & (1<<j)) != 0) {
					if (j> 0 && (mask >> (j-1) & 1) ==0 && nums[j] == nums[j-1]) {
						flag = false;
						break;
					}
					t.add(nums[j]);
				}
			}
			if (flag) {
				re.add(t);
			}
		}
		return re;
	}
	
}
