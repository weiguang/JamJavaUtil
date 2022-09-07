package com.okayjam.code.leetcode;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/09/06 19:28
 **/
public class DivideAndConquer {
	public static void main(String[] args) {
//	    System.out.println(new DivideAndConquer().myPow(2,2));
	    System.out.println(new DivideAndConquer().mySqrt(8));
	}

	/**
	 * 69. x 的平方根
	 * https://leetcode.cn/problems/sqrtx/
	 * @param x
	 * @return
	 */
	public int mySqrt(int x) {
		if (x < 2) return x;
		int left =1, right = x / 2;
		int mid=1,last_mid = 1;
		while (left <= right) {
			 mid = left + (right - left) / 2;
//			System.out.println(left+" " + mid + " " +right);
			if (x/mid > mid) {
				left = mid + 1;
				last_mid = mid;
			} else if (x/mid < mid) {
				right = mid -1;
			} else {
				return mid;
			}
		}
		return last_mid;
	}

	/**
	 * 50. Pow(x, n)
	 * https://leetcode.cn/problems/powx-n/
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow(double x, int n) {
		return n > 0 ? pow1(x,n) : 1.0 / pow1(x, -n);
	}

	public double pow1(double x, long n) {
		if (n == 0) return 1;
		double  re = pow1(x, n/2);
		return re * re * ((n&1) == 0 ? 1:x);

	}


}
