package com.okayjam.algorithm;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/19 17:10.
 */
public class MathUtil {

    /**
     * 判断素数 2,3,5,7,11...
     * @param n
     * @return
     */
    public static boolean isPrime(long n) {
        if(n < 2) return false;
        if(n == 2) return true;
        if(n % 2 == 0) return false;
        for (int i = 3; i <= java.lang.Math.sqrt(n); i+=2) {
            if(n % i == 0) return false;
        }
        return true;
    }

    /**
     * 计算整数n二进制1的个数
     * @param n
     * @return
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
     * @return
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
     * @return
     */
    public int NextN(int n){
        int x = n&(-n);
        int t = n + x;
        int ans = t | ((n^t)/x)>>2;
        return ans;
    }

}
