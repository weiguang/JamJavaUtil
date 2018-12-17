package job.guazi;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/10/25 15:59.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);

    public static void main (String[] args) throws Exception {
       // func1();
        int n = sn.nextInt();
        int[] bikeX = new int[n];
        int[] bikeY = new int[n];
        for (int i = 0; i < n; i++) {
            bikeX[i] = sn.nextInt();
        }
        for (int i = 0; i < n; i++) {
            bikeY[i] = sn.nextInt();
        }
        int[] home = new int[2];
        home[0] = sn.nextInt();
        home[1] = sn.nextInt();
        int[] speed = new int[2];
        speed[0] = sn.nextInt();
        speed[1] = sn.nextInt();

        System.out.println(getMinTime(n,bikeX,bikeY,home,speed));
    }

    static void func1() {
        System.out.println("Hello world!");
    }

    public static int getMinTime(int n, int[] bikeX, int[] bikeY, int[] home, int[] speed) throws Exception {
        // 请在此添加代码
        int min = (home[0] + home[1]) * speed[0];
        for (int i = 0; i < bikeX.length; i++) {
            int t = (home[0] - bikeX[0] + home[1] - bikeY[1]) * speed[1] + ( bikeX[0] + bikeY[1]) * speed[1];
            if (t < min) min = t;
        }
        return min;
    }

}
