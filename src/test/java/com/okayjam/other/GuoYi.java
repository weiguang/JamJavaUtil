package com.okayjam.other;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: ${description}
 * @author: Chen weiguang <weiguangchen@sf-express.com>
 * @create: 2018/08/23 10:05
 **/
public class GuoYi {
    static List<Integer> best = new ArrayList();
    static double bestRemain = Integer.MAX_VALUE;
    public static void main(String[] args) {
        //int [] length = {650,531,494,362,319};
       // int [] number = {80,123,63,117,78};
        //int [] number = {5,5,5,5,5};
        double [] length = {1304,1254,1154,1104,832,598.67,698.66,482,332,732,532.382,332};
        int [] number = {910,360,184,2,24,12,6,184,916,20,20,270,814};
        List currentList = new ArrayList();
        int currentLength = 3700;
        new GuoYi().getBest( currentList, currentLength, length ,  number );
        System.out.println(bestRemain);
        for (int v: best
             ) {
            System.out.print(v+ " ");
        }

    }
// 650 80
// 531 123
//    494 63
//    362 117
//    319 78
    public void getBest( List currentList,double currentLength, double [] length , int [] number ) {
        if(Math.abs(currentLength) < bestRemain) {
            bestRemain = Math.abs(currentLength);
            best.clear();
            best.addAll(currentList);
        }
        for (int i = 0; i < number.length; i++) {
            if(currentLength > 0 && number[i] > 0) {
                currentList.add(length[i]);
                number[i]--;
                getBest(currentList, currentLength - length[i],length,number);
                currentList.remove(currentList.size() - 1);
            }
        }
    }
}
