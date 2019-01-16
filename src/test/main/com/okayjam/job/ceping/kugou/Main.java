package main.com.okayjam.job.ceping.kugou;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/19 14:50.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main(String args[]) {
        func1();
    }

    public static void func1() {
        int n = sn.nextInt();
        int i = 0;
        System.out.println(random_n(n));
    }

    static int andbit(){
        if(Math.random() >0.5) return 1;
        else return 0;
    }

    static int random_n( int a)
    {
        int val = 0 ;
        int t = a + 1; // t为n最大倍数，且满足 t <= m * m
        do {
            val = 2 * (andbit() ) + andbit() +1;
        } while (val > t);
        return val - 1;
    }

}
