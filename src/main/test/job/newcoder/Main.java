package job.newcoder;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/1 20:39.
 */


import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {//注意while处理多个case
        int n = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
        }
        int re = getMul(data);
        System.out.println(re);


    }

    public static int getMul(int[] data){
        if(data.length < 0) return 0;
        int max = Integer.MIN_VALUE;
        if(data.length <=3){
            for (int i = 0; i <data.length; i++)  max*= data[i];
        }
        for (int i = 0; i <data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                for (int k = 0; k < data.length; k++) {
                    if( i!=j && j!=k && i!=k){
                       int t =  data[i] * data[j] * data[k];
                       if(t > max) max = t;
                    }
                }
            }
        }
        return max;
    }

    public static int getMul2(int[] data){
        if(data.length < 3) return 0;
        int max = Integer.MIN_VALUE;
        Arrays.sort(data,0,data.length-1);
        int[] m1 = new int[3];
        int[] m2 = new int[2];
        int t = 0;
        for (int i = 0; i < data.length && t<2; i++) {
            if(data[i] <0 ){ m2[t++] = data[i]; }
        }
        t = 0;
        for (int i = data.length - 1; t < 3 && i > 0; i--) {
            if(data[i] > 0) { m1[t++] = data[i];}
        }
        max = Math.max(m1[1] * m1[2], m2[0]*m2[1]);
        max *= m1[0];
        return max;
    }


}