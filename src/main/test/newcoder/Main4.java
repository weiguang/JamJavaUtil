package newcoder;

import java.util.*;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/28 14:40.
 */
public class Main4 {
    static Scanner sn  = new Scanner(System.in);
    public static void main (String[] args) {
//        System.out.println("Hello world!");
        getPrime();
    }

    public static boolean isPrime(long n) {
        if(n < 2) return false;
        if(n == 2) return true;
        if(n % 2 == 0) return false;
        for (int i = 3; i <= java.lang.Math.sqrt(n); i+=2) {
            if(n % i == 0) return false;
        }
        return true;
    }

    static void getPrime() {
        int m = sn.nextInt();
        int n = sn.nextInt();
        int sum = 1;
        if(m==1) {
            System.out.println(2);
        }

        for (int i = 3; sum < n; i+=2) {
            if(isPrime(i)) {
                sum++;
                if (sum >= m) {
                    System.out.print(i);
                    if (sum ==n || (sum - m + 1) % 10 == 0) System.out.println();
                    else  System.out.print(" ");
                }
            }
        }
    }


}
