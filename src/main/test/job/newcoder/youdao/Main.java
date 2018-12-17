package job.newcoder.youdao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/12 14:37.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    static int sum = 0;
    static List list = new ArrayList();
    public static void main (String[] args) {

        //seq1();
       // System.out.println(find01String(sn.nextLine()));
        //System.out.println(dependent(sn.nextInt(),sn.nextInt(),sn.nextInt(),sn.nextInt()));
        //String s = sn.next();
        //allPermutation(s.toCharArray(),0,s.length() - 1);
        //System.out.println(sum);
       // magicCoin();
//        System.out.println(reverse(sn.nextInt()));
//        stringFragment(sn.nextLine());
        permutation();
    }

    static int find01String(String src) {
        int sum = 0;
        if (src == null || src.length() < 1) {
            return 0;
        }
        int count = 1;
        char c = src.charAt(0);
        for (int i = 1; i < src.length(); i++) {
            if(c == '1' ){
                if(src.charAt(i) == '0') count++;
                else count = 1;
            }else if(c == '0') {
                if(src.charAt(i) == '1') count++;
                else {
                    count = 1;
                }
            }
            c = src.charAt(i);
            if(count > sum) sum = count;
        }
        return sum;
    }

    static void seq() {
        int n = sn.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = sn.nextInt();
            revese(data,0,i);
        }
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            if(i!=data.length -1) System.out.print(" ");
        }
    }
    public static void revese(int[] data, int i, int j){
        while(i < j) {
            swap(data,i,j);
            i++;j--;
        }
    }
    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    static void seq1() {
        int n = sn.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = sn.nextInt();
        }
//        int[] d = new int[n];
//        int index = data.length - 1;
//        int f = 1;
//        for (int i = data.length - 1; i >= data.length/2 ; i--) {
//            d[data.length - i - 1] = data[index--];
//             if(index >= 0) d[i] = data[index--];
//        }
//
//        for (int i = 0; i < d.length; i++) {
//            System.out.print(d[i]);
//            if(i!=d.length -1) System.out.print(" ");
//        }
        int k = n;
        for (int i = n-1; i >= 0 ; i-=2) {
            System.out.print(data[i]);
            if(k-- != 1) System.out.print(" ");
        }
        for (int i = n%2 ==0 ?0:1; i < n; i+=2) {
            System.out.print(data[i]);
            if(k-- != 1) System.out.print(" ");
        }
    }

    public static int  dependent(int x,int f,int d,int p){
        if(x*f > d) return d/x;
        while (x*f <= d) {
            if( x*(f+1) <= (d-p)) {
                f++;
                d-=p;
            }else break;
        }
        return f;
    }

    public static void allPermutation(char[] data,int from ,int to) {
        if(to < 1) return;
        if(from == to ){
            if(!list.contains(new String(data))){
                int start = 0;
                char before = 0;
                int dif = 0;
                for (char datum : data) {
                    if(start==1 && before != datum) dif++;
                    if(dif>1) break;
                    start =1;
                    before = datum;
                    // System.out.print(datum);
                }
                //System.out.println(new String(data));
                if(dif <= 1) sum++;
                //System.out.println();
                list.add(new String(data));
            }

        }else{
            for (int i = from; i < to; i++) {
                swap(data, from, i);
                allPermutation(data, from + 1, to);
                swap(data, from, i);
            }
        }
    }

    public static void swap(char[] data, int i, int j) {
        char temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void magicCoin() {
        int n = sn.nextInt();
         getCoin(n); System.out.println();


    }

    public static void getCoin(int n) {
        if (n <= 0 ) return ;
        if ((n & 1) == 1) {
            getCoin((n - 1)/2);
            System.out.print(1);
        }else {
            getCoin((n - 2)/2);
            System.out.print(2);
        }

    }

    public static int reverse(int n) {
        StringBuilder sb = new StringBuilder(n+"");
        sb = sb.reverse();
        int t = Integer.valueOf(sb.toString());
        return n+t;
    }

    public static void stringFragment(String s) {
        int count = 1;
        int sum = 0;
        char pre = 0;
        for (char c : s.toCharArray()) {
                if(c != pre) {count = 0; sum++;}
                else count++;
            pre = c;
        }

        System.out.printf("%.2f\n", (1.0 * s.length() / sum));
    }

    public static void permutation() {
        int ex = sn.nextInt();
        while (ex-- > 0) {
            int n  = sn.nextInt();
            int odd = 0;
            int even = 0;
            int even4 = 0;
            int t = 0;
            for (int i = 0; i < n; i++) {
                if((t = sn.nextInt()) %4 == 0 ) even4++;
                else if( (t&1) == 0) even++;
                else ++odd;
            }
            if(even4 < odd - 1) System.out.println("No");
            else if( even4 == odd - 1 && (even & 1) == 1) System.out.println("No");
            else System.out.println("Yes");
        }
    }
}
