package main.com.okayjam.job.newcoder.didi;

import java.util.*;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/26 14:42.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main (String[] args) {
        func1();
    }

    static void func1(){
        int n = sn.nextInt();
        int ans = 0;
        int[] data = new int[n+1];
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            data[i] = sn.nextInt();
            dp[i] = dp[i-1];
            
        }
        System.out.println(ans);

    }

    static void func2() {
        int n = sn.nextInt();
        System.out.println(GetUglyNumber_Solution(n));
    }

    static public int GetUglyNumber_Solution(int index) {
        int count = 0;
        int i;
        for(i=1;count<index;i++){
            if(isUgly(i))
                count++;
        }
        return i - 1;
    }

    static public boolean isUgly(int n){
        while(n%3==0)
            n = n/3;
        while(n%2==0)
            n = n/2;
        while(n%5==0)
            n = n/5;
        if(n==1)
            return true;
        else
            return false;
    }




}
