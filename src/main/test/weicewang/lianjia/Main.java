package weicewang.lianjia;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/19 16:32.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main (String[] args) {
//        int n = sn.nextInt();
//        int count = 1;
//        int p = 2;
//        for (int i = 3; count < n; i+=2) {
//            if(isPrime(i)){
//                p = i; count++;
//            }
//        }
//        System.out.println(p);
        System.out.println(fib(sn.nextInt()));
    }
    public static void add() {
        while(sn.hasNextInt()){
            System.out.println(sn.nextInt() + sn.nextInt());
        }
    }

    public static boolean isPrime(long n) {
        if(n < 2) return false;
        if(n == 2) return true;
        if(n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i+=2) {
            if(n % i == 0) return false;
        }
        return true;
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

    public static long fib(long n) {
        double sqrt5 = Math.sqrt(5);
        return (long)(Math.pow(((1 + sqrt5) / 2.0),n) / sqrt5 -
                Math.pow(((1 - sqrt5) / 2.0),n) / sqrt5);
    }
}
