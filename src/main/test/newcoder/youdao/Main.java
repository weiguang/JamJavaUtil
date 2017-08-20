package newcoder.youdao;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/12 14:37.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main (String[] args) {

        //seq1();
       // System.out.println(find01String(sn.nextLine()));
        System.out.println(dependent(sn.nextInt(),sn.nextInt(),sn.nextInt(),sn.nextInt()));
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
}
