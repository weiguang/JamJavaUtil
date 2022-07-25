package com.okayjam.baseTest;

import com.okayjam.algorithm.Algorithm;
import com.okayjam.util.MathUtil;
import org.junit.Test;


import java.util.Arrays;
import java.util.Random;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/1 15:21.
 */
public class AlgorithmTest {
    //@Test
    public void listAll() {
        String[] array = new String[] {"1", "2", "3","4"};
        //Algorithm.listAll(Arrays.asList(array),"");

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

    public void allPermutation() {
        int[] data={1,2,3};
        Algorithm.allPermutation(data,0, data.length);
    }

   // @Test
    public void testString () {
        Algorithm.chkRotation("cdab",4,"abcd",4);
    }

    public void test () {
//        System.out.println(MathUtil.random1ToN(2));
//        System.out.println(MathUtil.random(1,2));
        long startTime = System.nanoTime();
        int sum = 0;
        int all = 1000000;
        Random r = new Random();
        for (int i = 0; i <  all; i++) {
//            sum += (MathUtil.random1ToN(2) == 1 ? 1 : 0);
//            sum += (MathUtil.random1ToN(2, r) == 1 ? 1 : 0);
            sum += (MathUtil.random(1,2) == 2 ? 1 : 0);
        }
        System.out.println(1.0*sum/all);
        long endTime = System.nanoTime();
        long diffTime = endTime - startTime;
        System.out.println("\n Time cost(nano):" + 1.0 * diffTime/1000);
    }

    @Test
    public void  testAdd() {
//        System.out.println(Algorithm.minDistance("intention", "execution"));
        System.out.println(Algorithm.findKthLargest(new int[]{1,2,3,4,5}, 2));
//        MathUtil.test();
    }
}
