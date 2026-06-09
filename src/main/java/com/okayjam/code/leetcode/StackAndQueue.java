package com.okayjam.code.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author chen2
 */
public class StackAndQueue {
	public static void main(String[] args) {
//	    System.out.println(isValid("()[]{}"));
		System.out.println(largestRectangleArea(new int[]{2,1,5,6,2,3}));
	}

	// [73,74,75,71,69,72,76,73]
	//                ,72,76
	public int[] dailyTemperatures(int[] temperatures) {
		int len = temperatures.length;
		int[] ans = new int[len];
		Deque<Integer> stack = new LinkedList<>();
		for(int i = len -1; i >= 0; i--) {
			while(!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
				stack.pop();
			}
			ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
			stack.push(i);
		}
		return ans;
	}

	public int[] finalPrices(int[] prices) {
		int len = prices.length;
		int[] res = new int[len];
		Deque<Integer> stack  = new LinkedList<>();
		for (int i = len -1; i >= 0; i--) {
			while (!stack.isEmpty() && stack.peek() > prices[i]) {
				stack.pop();
			}
			res[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek();
			stack.push(prices[i]);
		}
		return res;
	}

	public int[] exclusiveTime(int n, List<String> logs) {
		Deque<String[]> stack = new LinkedList<>();
		Deque<Integer> curs = new LinkedList<>();
		int[] ans = new int[n];
		int cur;
		for(String log : logs) {
			String[] logSplit = log.split(":");
			if (logSplit[1].equals("start")) {
				stack.push(logSplit);
				curs.push(0);
			}  else {
				int idx = Integer.parseInt(logSplit[0]);
				String[] pop = stack.pop() ;
				cur = curs.pop();
				int dur = Integer.parseInt(logSplit[2]) - Integer.parseInt(pop[2]) + 1 - cur;
				ans[idx] += dur;
				if (!curs.isEmpty()) {
					cur += curs.pop() + dur;
					curs.push(cur);
				}
			}
		}
		return ans;
	}



	/**
	 * 239. 滑动窗口最大值
	 * https://leetcode.cn/problems/sliding-window-maximum/
	 * @param nums
	 * @param k
	 * @return
	 */
	public int[] maxSlidingWindow(int[] nums, int k) {
		int n = nums.length;
		int [] re = new int[n-k+1];
		Deque<Integer> queue = new LinkedList<>();
		for (int i = 0; i < k; i++) {
			while (!queue.isEmpty() && nums[i]> queue.peekLast()) {
				queue.removeLast();
			}
			queue.addLast(nums[i]);
		}
		re[0] = queue.peekFirst();
		for (int i = k,j = 1; i < n; i++) {
			if (nums[i-k] == queue.peekFirst()) {
				queue.removeFirst();
			}
			while (!queue.isEmpty() && nums[i]> queue.peekLast()) {
				queue.removeLast();
			}
			queue.addLast(nums[i]);
			re[j++] = queue.peekFirst();
		}
		return re;
	}

	/**
	 *  柱状图中最大的矩形
	 * https://leetcode.cn/problems/largest-rectangle-in-histogram/
	 * @param heights
	 * @return
	 */
	public static int largestRectangleArea(int[] heights) {
		int[][] re = new int[heights.length][2];
		re[0][0] = 0;
		re[0][1] = heights[0];
		int maxArea =  heights[0];
		for (int i = 1; i < heights.length; i++) {
			int w = i - re[i-1][0] + 1;
			int h = Math.min(re[i-1][1], heights[i]);
			int area = w * h;
			if (area <= heights[i]) {
				re[i][0] = i;
				re[i][1] = heights[i];
			} else {
				re[i][0] = re[i-1][0];
				re[i][1] =  h;
			}
			if (maxArea < Math.max(area, heights[i])) {
				maxArea = Math.max(area, heights[i]);
			}
		}
		return maxArea;
	}

	public static boolean isValid(String s) {
		Deque<Character> stack = new LinkedList<>();
		for (char c: s.toCharArray()) {
			switch (c) {
				case '(':
				case '[':
				case '{':
					stack.push(c);
					break;
				case ')':
					if (stack.isEmpty()) {
						return false;
					}
					char t =  stack.pop();
					if (t != '(') {
						return false;
					}
					break;
				case ']':
					if (stack.isEmpty()) {
						return false;
					}
					 t =  stack.pop();
					if (t != '[') {
						return false;
					}
					break;
				case '}':
					if (stack.isEmpty()) {
						return false;
					}
					 t =  stack.pop();
					if (t != '{') {
						return false;
					}
					break;
				default:
			}
		}
		if (!stack.isEmpty()) {
			return false;
		}
		return true;
	}


}
