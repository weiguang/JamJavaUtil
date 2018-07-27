package job.newcoder.didi;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/26 14:42.
 */
public class MainNeitui {
    static Scanner sn = new Scanner(System.in);
    static int k = 0;
    public static void main (String[] args) {
        func2();
    }

    static void func1() {
        long sum = 0;
        long max = Integer.MIN_VALUE;
        int a = 0;
        while (sn.hasNextInt()) {
            a = sn.nextInt();
            if(sum <= 0) {
                sum = a;
            }else  sum += a;
            if(max < sum) max = sum;
        }
        System.out.println(max);
    }

    static void func2(){
        String str = sn.nextLine();
        String[] strs = str.split("\\s");
        int k = sn.nextInt();
        Queue<Integer> integerPriorityQueue = new PriorityQueue<>(k);
        for (String s : strs) {
            Integer i = Integer.valueOf(s);
            if (integerPriorityQueue.size() < k) {
                integerPriorityQueue.add(i);
//            } else if (i > integerPriorityQueue.peek()) {
//                integerPriorityQueue.poll();
//                integerPriorityQueue.add(i);
            }
        }
        while(integerPriorityQueue.size() > 0)
        System.out.println(integerPriorityQueue.poll());
    }




}
