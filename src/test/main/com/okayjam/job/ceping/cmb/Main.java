package main.com.okayjam.job.ceping.cmb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/19 14:50.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main(String args[]) {
        //func1();
        System.out.println(find(3,10,1));
    }

    public static void func1() {
        int n = sn.nextInt();
        int i = 0;
        System.out.println((n));

        List<Integer> li = new ArrayList<>();
        li.add(6);
        li.add(9);
        List list = li;
        List<String> ls = list;
        System.out.println(ls.get(1));
    }

    static int find(int n, int b, int d) {
        if(d == n ) return b;
        int all = 3*b - d*2;
        return find(n,all,++d);
    }


}
