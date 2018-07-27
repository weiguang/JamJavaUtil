package job.newcoder.pingan;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/10/11 21:33.
 */
public class Main {
    public static void main (String[] args) {
        //func1();
        Scanner sn = new Scanner(System.in);
        System.out.println(func2(sn.nextInt()));
    }

    /**
     * 3开头的4位数，有且仅有1位数字出现2次。如3103,3898,3668等
     * 解: 分别出现两个3和没有出现两个3的情况，都是 9*8*3
     */
    static void func1() {
        System.out.println(9*8*3*2);
    }

    /**
     * n!中末尾0的个数
     * 解：主要是2和5的因子的个数，把5的倍数找出来即可。归纳得f(n!)= k + f(k!),k= n/5,递归求解，，，
     * @param n
     * @return
     */
    static int func2(int n) {
        if (n < 5) { return 0; }
        else { return (n / 5 + func2(n / 5)); }
    }
}
