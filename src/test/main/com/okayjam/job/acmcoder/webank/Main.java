package main.com.okayjam.job.acmcoder.webank;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/28 15:46.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main (String[] args) {
//        func1();

//        int num = sn.nextInt();
//        int max = 0;
//        for (int i = 2; i < num - 1; i++) {
//            int t = transform(num, i);
//            if( t > max) max =t;
//        }
//        System.out.println(max);

        func3();
    }


    static public int transform(int num,int n){
        //int array[]=new int[100];
        int location=0;
        int sum = 0;
        while(num!=0){
            int remainder=num%n;
            num=num/n;
            //array[location]=remainder;
            location++;
            sum+= remainder;
        }
        return  sum; //show(array,location-1);

    }


    static void func1 () {
        int a = sn.nextInt();
        int b = sn.nextInt();
        int c = sn.nextInt();
        System.out.println(2+ b-c-1);
    }

    static void func3() {
        long n = sn.nextLong();
//
//        long sum = 1;
//        for (int i = 1; i <= n; i+=2) {
//            String b = Integer.toBinaryString(i);
//            StringBuilder sb = new StringBuilder(b);
//            if (sb.reverse().toString().compareTo(b) == 0) sum++;
//
//        }
//        System.out.println(sum);
        int sum = 0;

        for (long i = 0; i <= n; i++)
        {
            if (fun(i)!=0)
                sum++;
        }
        System.out.println(sum);

    }

    static int fun(long n)
    {
        long a[] = new long[10000];
        long index = 0, x, y, i;

        x = n;
        y = n %2;
        while (x != 0)
        {
            a[(int)index] = y;
            index++;
            x = x / 2;
            y = x%2;
        }

        for (i = 0; i < index / 2;i++)
        {
            if (a[(int)i] != a[(int)(index - i - 1)])
            {
                return 0;
            }
        }
        return 1;
    }
    static boolean circle(int n,int d){
        if((n&1) ==0) return false;
        int s = 0;
        int m = n;
        while(m > 0){
            s = s * d + m % d;
            m /= d;
        }
        return s == n;
    }


}
