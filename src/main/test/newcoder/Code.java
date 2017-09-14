package newcoder;

import com.okayjam.util.Sort;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/7/29 9:52.
 */
public class Code {
    public static void main (String[] args) {
        Scanner in= new Scanner(System.in);
        int n = in.nextInt();
        int[][] data = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = in.nextInt();
            }
        }
        maxSubMatrix(data);
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

}
