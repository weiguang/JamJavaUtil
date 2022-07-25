package com.okayjam.util;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();

        String ch1 = cin.next();

        int ch = 0;

        if (ch1.length() == 1 ) {
            char ch2 = ch1.charAt(0);
            if (ch2 < '0' || ch2 > '9') {
                System.out.println(-1);
                return;
            } else {
                ch = ch2 -'0';
            }
        } else {
            System.out.println(-1);
            return;
        }


        int res[] = new int[10];

        res[0] = getCount0(n);
        for (int i = 1; i < 10; i++) {
            res[i] = getCount(n, i);
        }

        System.out.println(res[ch]);


    }

    public static int getCount(int num, int target) {

        int base = 1;
        int sum = 0;
        int n = num;

        while (n != 0) {

            sum += base * (n / 10);

            int cur = n % 10;
            if (cur == target) {
                sum += num % base + 1;
            } else if (cur > target) {
                sum += base;
            }

            base *= 10;
            n = n / 10;
        }

        return sum;

    }

    public static int getCount0(int num) {

        int base = 1;
        int sum = 0;
        int n = num;

        while (n != 0) {

            sum += base * (n / 10 -1);

            int cur = n % 10;
            if (cur == 0) {
                sum += num % base + 1;
            }
            else if (cur > 0) {
                sum += base;
            }

            base *= 10;
            n = n / 10;
        }

        return sum;

    }

}