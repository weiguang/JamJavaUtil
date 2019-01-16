package main.com.okayjam.job.chinahr.liebao;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/20 19:26.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main (String[] args) {
        func2();
    }

    public static void func1() {
        long stamp = sn.nextLong();
        long a = stamp/60;
        long b = stamp % 60;
        String s = "";
        s += ":"+ (b>10?b:"0"+b);
        b = a%60;
        a = a/60;
        s=":"+ (b>10?b:"0"+b) +s;
        b = a%24;
        a = a/24;
        s=" "+ (b>10?b:"0"+b) + s;
        b = a%30+1;
        a = a/30;
        s="/"+ (b>10?b:"0"+b) + s;
        b = a%12+1;
        a = a/12;
        s ="/"+ (b>10?b:"0"+b) + s;
        s = (1970  + a) + s;
        System.out.println(s);
    }

    public static void func2() {
        int m = sn.nextInt();
        int[] n =  new int[101];
        n[0] = 1;
        for (int i = 1; i != 101; i++)
            n[i] = n[i - 1] * i;
        for (int j = 0; j != m; j++)
        {
            int N = sn.nextInt();
            if (N == 1) {
                System.out.println(1);continue; }
            int sum = 0;
            for (int i = 2; i <= N-1; i++)
            {
                sum = (sum + i * n[N]/(n[N-i+1])) % 10000;
            }
            sum = (sum  + (1 + n[N]))%10000;
            System.out.println(sum);
        }
    }

}
