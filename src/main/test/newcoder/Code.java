package newcoder;

import com.okayjam.util.Sort;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/7/29 9:52.
 */
public class Code {
    @Test
    public void test(){
        int[] data = {9,7,5,3,4,6,4,2,0,8};
        changeOdd(data);
    }
    public void changeOdd(int[] data ) {
        Sort.bubbleSort(data);
        for (int datum : data) {
            System.out.print( datum + " ");
        }
    }
}
