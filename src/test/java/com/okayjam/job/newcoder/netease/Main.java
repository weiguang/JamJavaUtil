package com.okayjam.job.newcoder.netease;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/9 14:26.
 */
public class Main {
    static String s;
    static int max = 0;
    static int re = 0;
    public static void main(String args[]) {
        func3();
    }

    public static void func3(){
         Scanner in = new Scanner(System.in);
         s = in.nextLine();
        listAll(new StringBuilder(), s.length());
        System.out.println(re);
    }

    public static void listAll(StringBuilder candidate, int size ) {
        if(candidate.length() == size){
            String ca = candidate.toString();
            if(!s.equals(ca) && braket(ca)){
                //System.out.println(candidate.toString());
                //System.out.println(compute(s.toCharArray(), candidate.toCharArray()));
                int t = compute(s.toCharArray(), ca.toCharArray());
                if (t > max) {
                    max = t; re = 1;
                }else if( t == max) re++;
            }

        }else
        {
            candidate.append('(');
            listAll(candidate,size);
            candidate.deleteCharAt(candidate.length() -1);
            candidate.append(')');
            listAll(candidate,size);
            if(candidate.length() > 0)
                candidate.deleteCharAt(candidate.length() -1);
        }



    }

    public static boolean braket(String str){
        Stack<Character> s = new Stack<Character>();
        for(int i = 0 ; i< str.length(); i++){
            char c = str.charAt(i);
            switch(c){
                case '{':
                case '[':
                case '(':
                    s.push(c);
                    break;
                case '}':
                    if(!s.isEmpty() && s.pop()=='{')
                        break;
                    else return false;
                case ']':
                    if(!s.isEmpty() && s.pop()=='[')
                        break;
                    else return false;
                case ')':
                    if(!s.isEmpty() && s.pop()=='(')
                        break;
                    else return false;
            }
        }
        if(s.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    public static int compute(char[] str1, char[] str2)
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

}
