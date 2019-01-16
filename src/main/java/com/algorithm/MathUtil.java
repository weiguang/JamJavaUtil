package com.algorithm;

import java.util.*;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/19 17:10.
 */
public class MathUtil {

    /**
     * 判断素数 2,3,5,7,11...
     * @param n
     * @return 如果是素数返回true
     */
    public static boolean isPrime(long n) {
        if(n < 2) return false;
        if(n == 2) return true;
        if(n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i+=2) {
            if(n % i == 0) return false;
        }
        return true;
    }

    /**
     * 计算整数n二进制1的个数
     * @param n
     * @return 返回1的个数
     */
    public int count1Bits(int n){
        int count = 0;
        while(n!=0){
            count++;
            n = n & (n-1);
        }
        return count;
    }

    /**
     * 给定一个正整数 N，求最小的、比 N 大的正整数 M，使得 M 与 N 的二进制表示中有相同数目的 1
     * 简单枚举
     * @param n
     * @return 返回下一个和n具有相同1个数的数
     */
    public int GetNextN(int n){
        int k = count1Bits(n);
        do{
            n++;
        }while(count1Bits(n) != k);
        return n;
    }

    /**
     * 给定一个正整数 N，求最小的、比 N 大的正整数 M，使得 M 与 N 的二进制表示中有相同数目的 1
     * O(1)算法实现
     * @param n
     * @return 返回下一个和n具有相同1个数的数
     */
    public int NextN(int n){
        int x = n&(-n);
        int t = n + x;
        int ans = t | ((n^t)/x)>>2;
        return ans;
    }

    /**
     * 判断一个三位数是否是水仙花数
     * “水仙花数”是指一个三位数，它的各位数字的立方和等于其本身，比如：153=1^3+5^3+3^3
     * @param n
     * @return 如果是水仙花数返回true，否则返回false
     */
    public static boolean isNarcissisticNumber(int n){
        if(n<100 || n>999) return false;
        int a = n % 10, b = n / 10 % 10, c = n / 100;
        if( a*a*a + b*b*b + c*c*c == n) return true;
        return false;
    }

    /**
     * 生成随机数，1 - N
     * @param n
     * @param r
     * @return
     */
    public static int random1ToN(int n, Random r) {
        return r.nextInt(n) + 1;
    }

    /**
     * 生成随机数，1 - N
     * @param n
     * @return
     */
    public static int random1ToN(int n) {
        return 1 + (int)(Math.random()*n);
    }

    /**
     * 生成随机数，[min,max]
     * @param min
     * @param max
     * @return
     */
    public static int random(int min, int max) {
        return min + (int)(Math.random() * (max - min + 1));
    }

    /**
     * 没有加减乘除的加法
     * @param a
     * @param b
     * @return
     */
    public static int addWithoutOperator(int a, int b) {
        int sum,carry;
        do{
            sum = a ^ b;
            carry = (a & b) << 1;
            a = sum;
            b = carry;
        } while (b != 0);
        return sum;
    }

    // 递归法求最大公约数
    public static int maxCommonDivisor(int m, int n) {
        if (m < n) {// 保证m>n,若m<n,则进行数据交换
            int temp = m;
            m = n;
            n = temp;
        }
        if (m % n == 0) {// 若余数为0,返回最大公约数
            return n;
        } else { // 否则,进行递归,把n赋给m,把余数赋给n
            return maxCommonDivisor(n, m % n);
        }
    }

    // 求最小公倍数
    public static int minCommonMultiple(int m, int n) {
        return m * n / maxCommonDivisor(m, n);
    }



        public static void test(){
        System.out.println(addWithoutOperator(12,5));
    }

}
