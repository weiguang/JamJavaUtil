package com.okayjam.job.newcoder;

import com.okayjam.util.Sort;

import java.util.*;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/7/29 9:52.
 */
public class Code {
    static Scanner in= new Scanner(System.in);
    public static void main (String[] args) {

//        int n = in.nextInt();
//        int[][] data = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                data[i][j] = in.nextInt();
//            }
//        }
//        maxSubMatrix(data);
        merageMax();
    }

    public void test(){
        //int[] data = {9,7,5,3,4,6,4,2,0,8};
        //changeOdd(data);

    }
    public void changeOdd(int[] data ) {
        Sort.bubbleSort(data);
        for (int datum : data) {
            System.out.print( datum + " ");
        }
    }


    public static void maxSubMatrix(int  data[][]) {
        int n = data.length;
        int[] len = new int[n];
        int[] len1 = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                len1[j] = len[j] += data[i][j] <= 0 ? 0: len[j] +1;
            }
            Arrays.sort(len1);
            for (int j = 0; j < n; j++) {
                int re = len1[j] * (n-j);
                ans = re>ans?re:ans ;
            }
        }

        System.out.println(ans);
    }

    public static void merageMax(){
        List<Integer> list = new ArrayList();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            list.add(in.nextInt());
        }
        Collections.sort(list,new SortByNumber());
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
        }
    }

}


class SortByNumber implements Comparator<Integer>{
    @Override
    public int compare(Integer o1, Integer o2) {
        return  Integer.valueOf(o2+""+o1) - Integer.valueOf(o1+""+o2) ;

    }
}
