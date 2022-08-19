package com.okayjam.code.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/08/16 19:23
 **/
public class BruteForceAttack {
	public static void main(String[] args) {
	    new BruteForceAttack().permute(new int[]{1,2,3});
	}

	/**
	 * 77. 组合
	 * https://leetcode.cn/problems/combinations/
	 * @param n
	 * @param k
	 * @return
	 */
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> re = new ArrayList<>();
		int i = 0;
		while(i+k < n) {

		}
		return re;

	}


	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> re = new ArrayList<>();
		List<Integer> t = new ArrayList<>(nums.length + 1);
		boolean[]vis = new boolean[nums.length];
		Arrays.sort(nums);
		backtrackUnique(nums, re, t, vis, 0);
		return re;
	}

	public void backtrackUnique(int[] nums, List<List<Integer>> re, List<Integer> t, boolean[]vis, int first) {
		int n = nums.length;
		if (first == n) {
			re.add(new ArrayList<>(t));
		}
		for (int i = 0; i < n; i++) {
			if (vis[i] || (i > 0 && nums[i] == nums[i -1] && vis[i-1])) {
				continue;
			}
			t.add(nums[i]);
			vis[i] = true;
			backtrackUnique(nums, re, t, vis, first+1);
			vis[i] = false;
			t.remove(first);
		}
	}


	/**
	 * 46. 全排列
	 * https://leetcode.cn/problems/permutations/
	 * @param nums
	 * @return
	 */
	public  List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> re = new ArrayList<>();
		List<Integer> t = new ArrayList<>(nums.length + 1);
		for (int v : nums) {
			t.add(v);
		}
		backtrack(nums.length, re, t, 0);
		return re;
	}
	public void backtrack(int n, List<List<Integer>> re, List<Integer> t, int first) {
		if (first == n) {
			re.add(new ArrayList<>(t));
		}
		for (int i = first; i < n; i++) {
			Collections.swap(t, first, i);
			backtrack(n, re, t, first+1);
			Collections.swap(t, first, i);
		}
	}

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
