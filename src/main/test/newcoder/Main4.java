package newcoder;

import java.util.*;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/28 14:40.
 */
public class Main4 {
    static Scanner sn  = new Scanner(System.in);
    public static void main (String[] args) {
//        System.out.println("Hello world!");
//        getPrime();
//        System.out.println(Find(16,new int[][]{} ));
//        int b[] = methodName();
//        for (int i = 0; i < b.length; i++) {
//            System.out.println(b[i]);
//        }
        methodName();
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

    public static boolean Find(int target, int [][] array) {
        if(array == null || array.length == 0) return false;
        int[][] a = array;
        int n = array.length;
        int m = array[0].length;
        int i = n - 1, j = 0;
        while (i >= 0 && j < m) {
            if(a[i][j] > target) {
                if(i>0) i--;
                else return false;
            }else if(a[i][j] < target){
                if( j < m-1) j++;
                else return false;
            }else return true;
        }
        return  false;
    }

    /**
     *
     *
     * @author Weiguang Chen <chen2621978@gmail.com> on
     * @param
     * @return void
     */
    public static void methodName() {
        while (sn.hasNextInt()) {


            int n = sn.nextInt();
            int a[] = new int[1001];
            for (int i = 0; i < n; i++) {
                int s = sn.nextInt();
                a[s]++;
            }
            int j = 0;
            for (int i = 1; i <= 1000; i++) {
                if (a[i] > 0) {
                    a[j++] = i;
                    System.out.println(i);
                }
            }
//        int[] b = new int[j];
//        for (int i = 0; i < j; i++) {
//            b[i] = a[i];
//        }
//        return b;
//    }
        }
    }

}
