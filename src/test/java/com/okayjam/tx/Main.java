package com.okayjam.tx;

import com.okayjam.util.DownloadFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2020/08/04 19:19
 **/
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


    }

    static void m2() {
        int t = scanner.nextInt();
        while(t-- > 0) {
            int n = scanner.nextInt();
            long m = scanner.nextInt();
            int[] a = new int[n];
            int sumx = 0;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
                if (a[i] == -1) {
                    sumx++;
                } else {
                    sum += a[i];
                }
            }

            long min = -1;

            int x = 0;
            long cur = 0;
            for (int i = 0; i < 3; i++) {
                if (a[i] == -1) {
                    x++;
                } else {
                    cur += a[i];
                }
            }
            if(cur > m) min = sum;
            else {
               min =  sum + sumx * (long) Math.ceil( (m - cur) / x);
            }

            for (int i = 3; i < n ; i++) {
                if(a[i-3] != -1) {
                    cur -= a[i -3];
                } else {
                    x--;
                }

                if (a[i] != -1) {
                     cur += a[i];
                } else {
                    x++;
                    long per = 0;
                    if (cur < m) {
                        if (x == 0) {
                            continue;
                        } else {
                            per = (long) Math.ceil( (m - cur) / x);
                        }
                    }
                    long aa = sum + sumx * per;
                    if ( min == -1  || aa < min) {
                        min = aa;
                    }
                }
            }
            if (min == -1) {
                System.out.println("Impossible");
            } else {
                System.out.println(min);
            }
        }
    }


   void  m1() {
       int t = scanner.nextInt();
       while(t-- > 0) {
           int n = scanner.nextInt();
           if (n > 0) {
               System.out.println("You are the future of Tencent!");
           } else {
               System.out.println("Good luck and Enjoy TPC!");
           }
       }
    }
}
