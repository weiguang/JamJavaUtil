package com.okayjam.baseTest;



import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/8 10:39.
 */
public class CollectionTest {


    @Test
    public void testStream() {
        List<Double> list = new ArrayList(1000000);
        for (int i = 0; i < 10; i++) {
            list.add(Math.random());
        }
        long be;
        long end;
        List<Double> list1;



        be = System.nanoTime();

        list.stream().parallel().forEach(p -> test());

        end = System.nanoTime();
        System.out.println("Stream: " + (end - be) / 1E6);


        be = System.nanoTime();
        list1 = new ArrayList<>(list.size());
        for (Double aDouble : list) {
//            if(aDouble > 0.2) {
//                list1.add(aDouble);
//            }
            test();
        }
//        Collections.sort(list1);
        end = System.nanoTime();
        System.out.println("normal: " + (end - be) / 1E6);






/*        be = System.nanoTime();
        Double max1 = -1d;
        for (Double aDouble : list) {
            if (aDouble.compareTo(max1) > 0) {
                max1 = aDouble;
            }
        }
        end = System.nanoTime();
        System.out.println("for max:\t " + max1 + "," + (end - be));

        be = System.nanoTime();
        double max = list.stream().max(Double::compareTo).get();
        end = System.nanoTime();
        System.out.println("Stream max:\t " + max + "," + (end - be));*/

//        System.out.println(list1.size());
//        list1.stream().forEach(System.out::println);
    }

    public void test() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 测试集合 Set
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/8/8 10:39
     */
//    @Test
    public void testSet() {
        TreeSet tset = new TreeSet(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int flag = (o1 & 1) - (o2 & 1);
                if(flag == 0) flag = o1 - o2;
                return flag;
            }
        });

        TreeSet set2 = new TreeSet(new MyComparator());
        tset = set2;

        tset.add(1);
        tset.add(1);
        tset.add(new Random().nextInt(100));
        tset.add(new Random().nextInt(100));
        tset.add(new Random().nextInt(100));
        tset.add(new Random().nextInt(100));
        tset.add(new Random().nextInt(100));

//        for (Object o : tset) {
//            System.out.println(o);
//        }

        Iterator it = tset.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }

    }

    /**
     *
     *
     * @author Weiguang Chen <chen2621978@gmail.com>
     * @param
     * @return void
     */
    @Test
    public void testNull() {
        TreeSet set = new TreeSet();
        //set.add(null);    //GuoYi.lang.NullPointerException
        TreeMap map = new TreeMap();
        //map.put(null,123);    //GuoYi.lang.NullPointerException
        List list = new ArrayList();
        list.add(null);     //okay!
        list.add("I am ");
        list.add(123);
        System.out.println(list);
        HashMap hashMap = new HashMap();
        hashMap.put(null,123);
        if(hashMap.containsKey(null)) {
            System.out.println("contain null, size:"+ hashMap.size());
        }
    }

    class MyComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            int flag = (o1 & 1) - (o2 & 1);
            if(flag == 0) flag = o1 - o2;
            return flag;
        }
    }
}
