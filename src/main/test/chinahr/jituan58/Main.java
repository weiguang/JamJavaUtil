package chinahr.jituan58;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/20 19:26.
 */
public class Main {
    Scanner sn = new Scanner(System.in);
    public static void main (String[] args) {

        func1();
        System.out.println(binarySearchMin(new int[]{4,3,2,1,5,6},6));
    }

    public static void func1() {

    }

    static public int binarySearchMin(int[] data, int arrLen) {
        int start = 0, end = arrLen -1;
        int mid = start;
        while (data[start] >= data[end]) {
            // 仅有两个元素时,第二个元素为最小值
            if (start + 1 == end) {
                return data[end];
            }

            mid = (start + end) >> 1;
            // 顺序查找
            if (data[mid] == data[start] && data[mid] == data[end]) {
                return seqSearchMin(data, start, end);
            }
            // 最小元素位于右半区间
            if (data[mid] >= data[start]) {
                start = mid;
            } else if (data[mid] <= data[start]) {        // 最小元素位于左半区间
                end = mid;
            }
        }
        return data[mid];
    }

    /**
     * 遍历数组，寻找最小值
     * @param data  数组
     * @param start 数组首元素索引
     * @param end   数组尾元素索引
     * @return  最小值
     */
    static public int seqSearchMin(int data[], int start, int end) {
        int min = data[start];
        for (int i = start;i <= end; i++) {
            if (data[i] < min) {
                min = data[i];
            }
        }
        return min;
    }

}
