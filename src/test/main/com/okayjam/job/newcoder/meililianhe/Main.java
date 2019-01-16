package main.com.okayjam.job.newcoder.meililianhe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/23 14:06.
 */
public class Main {
    static final  int noleap[] = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
    static final int   leap[] = {0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
    public static void main (String[] args) {
        //System.out.println("Hello world!");
        Scanner sn = new Scanner(System.in);
        //System.out.println(calDayOfYear(sn.nextInt(), sn.nextInt(), sn.nextInt()));
        System.out.println(foo(5));
    }

    static int foo(int n) {
        if(n<2) return n;
        return foo(n-1)+2*foo(n-2);
    }

    public static int calDayOfYear(int year,int month, int day) {
        //return day + (isLeapYear(year)?leap[month - 1]: noleap[month -1]);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date d = format.parse(year + "-" + month + "-" + day);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            return c.get(Calendar.DAY_OF_YEAR);
        }catch (Exception ex) {ex.printStackTrace();}
        return 0;
    }

    final public static boolean isLeapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0 ) ? true:false;
    }
}
