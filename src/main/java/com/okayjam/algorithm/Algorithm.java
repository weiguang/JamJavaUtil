package com.okayjam.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/1 15:19.
 */
public class Algorithm {
    /**
     * 全排列
     *
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/17 21:25
     *
     */
    public static void listAll(List candidate, String prefix) {
        System.out.println(prefix);

        for (int i = 0; i < candidate.size(); i++) {
            List temp = new LinkedList(candidate);
            Object s = temp.remove(i);
            //System.out.println("prefix :" + prefix + " temp.remove(i):"+s);
            listAll(temp, prefix + s);
        }
    }

    /**
     * 约瑟环问题
     *
     * @author Weiguang Chen <chen2621978@gmail.com> on 2017/7/26 19:43
     * @param number
     */
    public static void yuse(int number) {
        boolean[] que = new boolean[number];
        int leftCount = que.length;
        int countNum = 0;
        int index = -1;
        while(leftCount > 0) {
            index = ++index % que.length;
            if(que[index]) { continue;}
            if(++countNum == 9){
                countNum = 0;
                que[index] = true;
                leftCount --;
                System.out.println(index +1 );
            }

        }
//        for (int i = 0; i < que.length; i++) {
//            if (que[i] == false ) {
//                System.out.print(i + " ");
//            }
//        }
    }

}
