package main.com.okayjam.job.acmcoder.sogou;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/8 15:47.
 */
public class Main {
    //static Scanner sn = new Scanner(System.in);
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static double a[];
    public static void main(String args[]) {
        try {
            func1();
        }catch (Exception ex) {

        }

    }

    public static void func1() throws Exception{
        //n = sn.nextInt();
        n = Integer.parseInt(reader.readLine());
        a = new double[n];
        for ( int i = 0 ; i < n ; ++ i )
            //a[i] = sn.nextDouble();
            a[i] = Double.parseDouble(reader.readLine());
        solve();
    }

    static double dis(double x, double y) {
        if ( Math.abs(x - y) > 180) return 360 - Math.abs(x - y);
        return Math.abs(x - y);
    }

    static void solve() {
        double ans = 0;
        //if ( n <= 10) {
            for ( int i = 0; i < n; ++ i )
                for ( int j = n - 1; j > i; j-- ){
                    double re =  dis(a[i], a[j]);
                    if(re > ans) ans = re;
                    else break;
                }

//        }else {
//            for ( int i = 0, j = 0; i < n; ++ i ) {
//                while ( dis(a[i], a[j]) < dis(a[i], a[(j + 1) % n])) j = (j + 1) % n;
//                ans = Math.max(ans, dis(a[i], a[j]));
//            }
//        }
//        if(n > 2) {
//            double re = 360 - a[n-1] + a[0];
//            if (re > ans) ans = re;
//        }

        System.out.printf("%.8f",ans);
    }

}
