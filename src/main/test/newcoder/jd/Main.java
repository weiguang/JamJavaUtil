package newcoder.jd;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/8 18:31.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main(String args[]) {
        func1();
    }

    public static void func1(){
//        while (sn.hasNextInt()) {//注意while处理多个case
//            int a = sn.nextInt();
//            int b = sn.nextInt();
//            System.out.println(a + b);
//        }
        String s = sn.next();
        int sum = 0;
        int t = 0;
        for (char c : s.toCharArray()) {
            if( c== '(') t++;
            else if(c == ')'  ) {
                if(sum == 1 && t == 1) t--;
                sum+=t;
                t = 0;
            }
        }
        if(t > 0) sum += t;
        t = 1;
        for (int i = sum; i > 0 ; i--) {
            t *= i;
        }
        if(s.isEmpty()) t = 0;
        System.out.println(t);
    }

    public static void func2(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                double re = Math.pow(i, j);

            }
        }
    }
}
