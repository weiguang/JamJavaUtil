package com.okayjam.algorithm;

import com.okayjam.util.Sort;

import java.util.*;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/1 15:19.
 */
public class Algorithm {

    /**
     * 全排列
     *
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/17 21:25
     *
     */
    public static void listAll(List candidate, String prefix) {
        System.out.println(prefix);

        for (int i = 0; i < candidate.size(); i++) {
            List temp = new LinkedList(candidate);
            Object s = temp.remove(i);
            //System.out.println("prefix :" + prefix + " temp.remove(i):"+s);
            listAll(temp, prefix + s);
        }
    }

    /**
     * 约瑟环问题
     *
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/26 19:43
     * @param number
     */
    public static void yuse(int number) {
        boolean[] que = new boolean[number];
        int leftCount = que.length;
        int countNum = 0;
        int index = -1;
        while(leftCount > 0) {
            index = ++index % que.length;
            if(que[index]) { continue;}
            if(++countNum == 9){
                countNum = 0;
                que[index] = true;
                leftCount --;
                System.out.println(index +1 );
            }

        }
//        for (int i = 0; i < que.length; i++) {
//            if (que[i] == false ) {
//                System.out.print(i + " ");
//            }
//        }
    }

    /**
     * 菲波那切数列 Fibonacci
     * 对应有 通项公式求解，递归求解和 非递归求解
     * @param n
     * @return
     */
    public static long fib(long n) {
        double sqrt5 = Math.sqrt(5);
        return (long)(Math.pow(((1 + sqrt5) / 2.0),n) / sqrt5 -
                Math.pow(((1 - sqrt5) / 2.0),n) / sqrt5);
    }

    /**
     * Fibonacci 数列递归版本
     * @param n
     * @return
     */
    public static long fibRecursion(long n){
        return n <= 1 ? n: fib(n - 1) + fib(n - 2);
    }

//    public static long fib(long n) {
//        if(n <= 1) return n;
//        int[] f = new int[2];
//        f[0] = 0; f[1] = 1;
//        int t = 0;
//        for (int i = 2; i <= n; i++) {
//            t = f[0] + f[1];
//            f[0] = f[1];
//            f[1] = t;
//        }
//        return f[1];
//    }

    /**
     * 全排列
     * @param data
     * @param from
     * @param to
     */
    public static void allPermutation(int[] data,int from ,int to) {
        if(to < 1) return;
        if(from == to ){
            for (int datum : data) {
                System.out.print(datum);
            }
            System.out.println();
        }else{
            for (int i = from; i < to; i++) {
                Sort.swap(data, from, i);
                allPermutation(data, from + 1, to);
                Sort.swap(data, from, i);
            }
        }
    }

    /**
     * 第一个出现的没有重复的单词
     * @param s
     * @return
     */
    public static char firstNotRepeatingChar(String s) {
        if (s == null || s.length() < 1) {
            throw new IllegalArgumentException("Arg should not be null or empty");
        }
        Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {
                map.put(c, -1);
            } else {
                map.put(c, 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return '\0';
    }

    /**
     * 旋转词
     * 如果对于一个字符串A，将A的前面任意一部分挪到后边去形成的字符串称为A的旋转词。
     * 比如A="12345",A的旋转词有"12345","23451","34512","45123"和"51234"。
     * @param A
     * @param lena
     * @param B
     * @param lenb
     * @return
     */
    public static boolean chkRotation(String A, int lena, String B, int lenb) {
        if (lena != lenb) return false;
        for (int i = 0; i < lena; i++) {
            int j;
            for (j = 0; j < lenb; j++) {
                if(B.charAt(j) != A.charAt( (i + j) % lena)) break;
            }
            if(j == lenb) return true;
        }
        return false;
    }

    public static int LCSubsequence(char[] str1, char[] str2)
    {
        int substringLength1 = str1.length;
        int substringLength2 = str2.length;

        // 构造二维数组记录子问题A[i]和B[j]的LCS的长度
        int[][] opt = new int[substringLength1 + 1][substringLength2 + 1];

        // 从后向前，动态规划计算所有子问题。也可从前到后。
        for (int i = substringLength1 - 1; i >= 0; i--)
        {
            for (int j = substringLength2 - 1; j >= 0; j--)
            {
                if (str1[i] == str2[j])
                    opt[i][j] = opt[i + 1][j + 1] + 1;// 状态转移方程
                else
                    opt[i][j] = Math.max(opt[i + 1][j], opt[i][j + 1]);// 状态转移方程
            }
        }
        // System.out.println("substring1:" + new String(str1));
        // System.out.println("substring2:" + new String(str2));
        //System.out.print("LCS:");

        int i = 0, j = 0;
        while (i < substringLength1 && j < substringLength2)
        {
            if (str1[i] == str2[j])
            {
                //System.out.print(str1[i]);
                i++;
                j++;
            }
            else if (opt[i + 1][j] >= opt[i][j + 1])
                i++;
            else
                j++;
        }
        //System.out.println();
        return opt[0][0];
    }

    /**
     * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        int n = word1 == null ? 0 : word1.length();
        int m = word2 == null ? 0 : word2.length();

        // 有一个字符串为空串
        if (n * m == 0)
            return n + m;

        // DP 数组
        int [][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1))
                    left_down += 1;
                D[i][j] = Math.min(left, Math.min(down, left_down));

            }
        }
        return D[n][m];
    }


    /**
     * 前k大(小)的数
     * @param nums 数组
     * @param k 前k的数
     * @return 对应的数
     */
    public static Integer findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(k + 2);
        // 前k小的数
//        PriorityQueue<Integer> q = new PriorityQueue<>(k + 2, Comparator.comparing(Integer::intValue).reversed());
//        PriorityQueue<Integer> q = new PriorityQueue<>(k + 2, Collections.reverseOrder());
        for (int i : nums) {
            q.offer(i);
            if (q.size() > k) {
                q.poll();
            }
        }
        return q.peek();
    }

    public static int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }


    public static  int countOne(int n) {
        int re = 0, flag = 0;
        for (int i=0; i< 32 && n > 0; i++) {
                if ((n & 1) == 1 ) {
                    if (flag == 0 ) {
                        re++; flag=1;
                    }
                } else {
                    flag =0;
                }
            n = n>>1;
        }
        while (n > 0) {
            if ((n & 1) == 1 ) {
                if (flag == 0 ) {
                    re++; flag=1;
                }
            } else {
                flag =0;
            }
        }
        return re;
    }

public static void main(String[] args) {
    System.out.println(String.format("%s, %d", Integer.toBinaryString(15), countOne(15)) );
    System.out.println(String.format("%s, %d",  Integer.toBinaryString(100), countOne(100)) );
    System.out.println(String.format("%s, %d", Integer.toBinaryString(21), countOne(21)) );

}

    public double fee(int n) throws Exception{
        if (n < 0) {
            throw new Exception("");
        }
        double price = 0;
        if (n > 6000) {
            price = 1.0;
        } else if (n > 1000) {
            price = 7 - (n-1)/1000;
        } else if (n > 500) {
            price = 7;
        } else if (n > 100) {
            price = 8;
        } else if (n > 50) {
            price = 9;
        } else if (n > 20) {
            price = 10;
        } else {
            price = 0;
        }
        return price * n;
    }
}
