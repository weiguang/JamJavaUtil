package com.okayjam.test;

import java.util.*;
import java.util.concurrent.*;

/*
 * @desc ArrayList遍历方式和效率的测试程序。
 *
 * @author skywang
 */
public class ArrayListRandomAccessTest {

        static StringBuffer sb = new StringBuffer(1000000);
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i=0; i<100000; i++)
            list.add(Math.random());



        //sb = new StringBuffer(100000);
        iteratorThroughIterator(list) ;
        //isRandomAccessSupported(list);
        //sb = new StringBuffer(100000);
        iteratorThroughRandomAccess(list) ;
        // sb = new StringBuffer(100000);
        iteratorThroughFor2(list) ;
    }

    private static void isRandomAccessSupported(List list) {
        if (list instanceof RandomAccess) {
            System.out.println("RandomAccess implemented!");
        } else {
            System.out.println("RandomAccess not implemented!");
        }

    }

    public static void iteratorThroughRandomAccess(List list) {

        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();

        for (int i=0; i<list.size(); i++) {
            //list.get(i);
            sb.append(list.get(i));
        }
        endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println("iteratorThroughRandomAccess：" + interval+" ms");
    }

    public static void iteratorThroughIterator(List list) {

        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        for(Iterator iter = list.iterator(); iter.hasNext(); ) {
            //iter.next();
            sb.append( iter.next());
        }
        endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println("iteratorThroughIterator：" + interval+" ms");
    }


    public static void iteratorThroughFor2(List list) {

        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        for(Object obj:list)
            sb.append(obj);
        endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println("iteratorThroughFor2：" + interval+" ms");
    }
}