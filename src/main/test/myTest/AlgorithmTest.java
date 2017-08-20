package myTest;

import com.okayjam.algorithm.Algorithm;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/1 15:21.
 */
public class AlgorithmTest {
    //@Test
    public void listAll() {
        String[] array = new String[] {"1", "2", "3","4"};
        Algorithm.listAll(Arrays.asList(array),"");
    }

    /**
     * 约瑟环问题
     *
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/26 19:43
     */
//    @Test
    public void yuse() {
       Algorithm.yuse(15);
    }

    @Test
    public void allPermutation() {
        int[] data={1,2,3};
        Algorithm.allPermutation(data,0, data.length);
    }
}
