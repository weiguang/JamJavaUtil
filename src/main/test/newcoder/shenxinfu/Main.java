package newcoder.shenxinfu;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/10/13 19:00.
 */
public class Main {
//    public static void main (String[] args) {
//        func1();
//    }

    static void func1() {
        System.out.println("Hello world!");
        int a = 3;
        int c = 0;
        if(a>0) if(a>3) c=2;else c=3;else c=4;
        System.out.println(c);
    }

    static int[] sumN = null;
    public static void main(String[] args) {
        int N = 21;
        int j = 0;
        int num = (N>>1)+1, i = 0;
        int current_sum = 0;
        sumN(num);
        while (j <= num) {
            current_sum = sumN[j] - sumN[i];
            if (current_sum == N) {
                System.out.println("i=" + (i + 1) + ",j=" + j);
                i++;
            } else if (current_sum < N) {
                j++;
            } else {
                i++;
            }
        }
    }

    public static void sumN(int count) {
        sumN = new int[count + 1];
        for (int i = 1; i <= count; i++) {
            sumN[i] = ((i + 1) * i) >> 1;
        }
    }
}
