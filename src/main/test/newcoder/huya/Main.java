package newcoder.huya;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/18 19:39.
 */
public class Main {
    static Scanner in  = new Scanner(System.in);
    public static void main (String[] args) {
        func1();
    }

    public static void func1() {
        String[] dic = new String[]{"中国","直播","游戏","游戏直播","互动直播平台"};
        boolean[] f = new boolean[dic.length];
        String s = in.nextLine();
        int pos = 0;
        while(pos < s.length()) {
            int min = -1;
            int mi = -1;
            String src;
            if(pos + 6 >= s.length()) src = s.substring(pos);
             else src = s.substring(pos,pos+6);
            pos+=6;
            for (int i = 0; i < dic.length; i++) {
                int index = src.indexOf(dic[i]);
                if(index != -1) {
                    if(min == -1 ) {min = index;mi = i;}
                    else if(index < min) {min = index;mi =i;}
                }
            }
            if(mi!=-1) f[mi] = true;
        }
        String s1 = "";
        for (int i = 0; i < dic.length; i++) {
            if(f[i])
                s1+=dic[i] +",";
        }
        if(s1!= null) System.out.println(s1.substring(0,s1.length() -1));

    }



}
