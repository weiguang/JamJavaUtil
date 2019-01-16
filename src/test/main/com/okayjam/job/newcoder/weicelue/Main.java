package main.com.okayjam.job.newcoder.weicelue;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/10/24 18:59.
 */
public class Main {
    public static void main (String[] args) {
        //System.out.println("Hello world!");
        f();
    }

    static void f() {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            double r = Math.pow(7,i);
            for (int j = 1; j <= 100; j++) {
                r += Math.pow(7, j);
                System.out.print((int)r + " ");
                if(r % 5 == 0) sum++;
            }
        }
        System.out.println(sum + " ,p = " + 1.0* sum / (100*100));
    }
}
