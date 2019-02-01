package com.okayjam.job.baidu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/19 18:56.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main(String args[]) {
        func1();
    }

    public static void func1() {


    int n = 4, P = 3, Q = 1;
    int[]  health = {9, 8, 2, 5};
    System.out.println(minimumNumberOfGunShots(n, P, Q,  health));
    }
    int findfence(int numHouse, int[][] coordinateList)
    {
        if(coordinateList.length <3) return  coordinateList.length;
        int ans = 0;
        int xx = coordinateList[0][0], xn = coordinateList[0][0], yx = coordinateList[0][1], yn = coordinateList[0][1];
        for (int i = 0; i < coordinateList.length; i++) {
            if(coordinateList[i][0] < xn) xn = coordinateList[i][0];
            else if( coordinateList[i][0] > xx) xx = coordinateList[i][0];
            if(coordinateList[i][1] < yn ) yn = coordinateList[i][1];
            else if(coordinateList[i][1] > yx) yx = coordinateList[i][1];
        }
        int ding[] = new int[4];
        List<Integer> list1 = new ArrayList<>();
        int txn = Integer.MAX_VALUE, txx = Integer.MIN_VALUE;
        int txn1 = Integer.MAX_VALUE, txx1 = Integer.MIN_VALUE;
        List<Integer> list2 = new  ArrayList();
        for (int i = 0; i < coordinateList.length; i++) {
            if(coordinateList[i][1] == yn) {
                list1.add(i);
                if(coordinateList[i][0] < txn) {txn = coordinateList[i][0];}
                else if (coordinateList[i][0] > txx) {txx = coordinateList[i][0];}
            }
             else if(coordinateList[i][1] == yx) {
                list2.add(i);
                if(coordinateList[i][0] < txn1) {txn1 = coordinateList[i][0];}
                else if (coordinateList[i][0] > txx1) {txx1 = coordinateList[i][0];}
            }
        }
        if(list2.size() == 0) return list1.size();

        if(list1.size() >2){
            ding[2] = list1.get(0);
            ding[3] = list1.get(list1.size() - 1);
        }else {
            ding[2] = ding[3] = list1.get(0);
        }

        if(list2.size() >2){
            ding[0] = list2.get(0);
            ding[1] = list2.get(list1.size() - 1);
        }else {
            ding[0] = ding[1] = list2.get(0);
        }

        return ans;
    }

    static public int minimumNumberOfGunShots(int num, int shotDegrade, int remDegrade,
                                       int[] health)
    {
        int max = 0;
        int res = 0;
        int p = shotDegrade;
        int q = remDegrade;
        int n = num;
        boolean flag = true;
        while (flag) {
            max = getMax( health);
               health[max] -= p;
              for (int i = 0; i < n; i++) {
                          if (i == max)
                           continue;
                           health[i] -= q;
              }
//                 System.out.println( healthays.toString( health));
              res++;
              flag = false;
              for (int i = 0; i < n; i++) {
                          if ( health[i] > 0)
                           flag = true;
              }
             }
             return res;
 }
         private static int getMax(int[]  health) {
              int max = 0;
             int index = 0;
             for (int i = 0; i <  health.length; i++) {
              if ( health[i] > max) {
                          max =  health[i];
                          index = i;
              }
             }
             return index;
 }
    

}
