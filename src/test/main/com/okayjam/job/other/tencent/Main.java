package main.com.okayjam.job.other.tencent;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/13 14:37.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main (String[] args) {
        func1();
    }

    static void func1(){
        int a = sn.nextInt();
        int b  = sn.nextInt();
        int A = sn.nextInt();
        int B = sn.nextInt();
        int count = f(a, b, A, B, 0);
        System.out.println(count);

    }
    static int f(int a,int b, int A, int B, int ant) {
        if(a == A  && b ==B) return ant;
        if(a>A || b > B) return -1;
        int count = f(a + 1, b  + 1, A, B, ant + 1);
        int count1 = f(a * 2, b  * 2, A, B, ant + 1);
        if(count == -1 && count1 == -1) return -1;
        if(count == -1) return count1;
        if(count1 == -1) return count;
        return count<count1?count:count1;
    }
}
