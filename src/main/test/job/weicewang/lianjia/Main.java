package job.weicewang.lianjia;

import java.io.*;
import java.util.*;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/19 16:32.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main (String[] args) {
        func3();
    }

    public static void func1(){
       int n = sn.nextInt();
       int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sn.nextInt();
        }
        int q = sn.nextInt();
        for (int i = 0; i < q; i++) {
            int qi = sn.nextInt();
            int  sum = 0, j = 0;
            for (j = 0; j < n; j++) {
                sum+=a[j];
                if(qi <= sum) break;
            }
            System.out.println(j+1);
        }
    }

    public static void func2() {
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = sn.nextInt();
        }
        int cha = 30;
        int high = sn.nextInt();
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            if(high + cha >= a[i]) sum++;
        }
        System.out.println(sum);
    }

    public static void func3()  {
        Scanner sn1 = sn;
        try {
            //sn1 = new Scanner(new BufferedReader(new FileReader("random.in")));
        TreeSet set = new TreeSet();
        int n = sn1.nextInt();
        for (int i = 0; i < n; i++) {
            set.add(sn1.nextInt());
        }
        BufferedWriter out = null;

        //out = new BufferedWriter(new FileWriter("random.out"));
        //out.write(set.size()+"\n");

        System.out.println(set.size());

        StringBuilder sb = new StringBuilder();
        for (Object o : set) {
            sb.append(o+" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.print(sb.toString());
//        out.write(sb.toString() + "\n");
//        out.flush();
//        out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
