package job.acmcoder.meituan;

        import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/31 19:03.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main(String args[]) {
        //func1();
        func2();
    }

    public static void func1(){
        while (sn.hasNext()) {
            int ans = 0;
            int n = sn.nextInt();
            int[] data = new int[n];
            //int sum = 0;
            for (int i = 0; i < n; i++) {
                data[i] = sn.nextInt();
                //sum += data[i];
            }
            int k = sn.nextInt();
            for (int i = 0; i < n; i++) {
                int s = data[i];
                for (int j = i + 1; j < n; j++) {
                    s += data[j];
                    if ((s % k ) == 0 && j-i >= ans)  ans = j - i + 1;
                    if(ans > n - i) break;
                }
            }
            System.out.println(ans);
        }
    }

    public static void func2 () {
        int n = sn.nextInt();
        long tot = 0;
        int m = 0, x = 0;
        for (int i = 0; i < n; i++) {
            x = sn.nextInt();
            tot += x;
            m = Math.max(m, x);
        }
        if(m <= tot - m) System.out.println("Yes");
        else System.out.println("No");
    }
}
