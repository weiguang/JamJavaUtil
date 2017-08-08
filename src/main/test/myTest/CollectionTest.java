package myTest;

import org.junit.Test;

import java.util.*;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/8 10:39.
 */
public class CollectionTest {
    /**
     * 测试集合 Set
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/8/8 10:39
     */
    @Test
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

    class MyComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            int flag = (o1 & 1) - (o2 & 1);
            if(flag == 0) flag = o1 - o2;
            return flag;
        }
    }
}
