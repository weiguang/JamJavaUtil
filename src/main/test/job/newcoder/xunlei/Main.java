package job.newcoder.xunlei;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/9/18 19:39.
 */
public class Main {
    static Scanner in  = new Scanner(System.in);
    public static void main (String[] args) {
        func1(in.nextInt());
    }

    public static void func1(int n) {
        int ans = 0;
        for (int i = 2; i <= 5; i++) {
            String s = baseString(n, i);
            //System.out.println(s);
            for (int j = 0; j < s.length(); j++) {
                ans += Integer.valueOf(s.charAt(j)+"");
            }
        }
        System.out.println(ans);

    }

    public static String baseString(int num,int base){
        if(base > 16){
            throw new RuntimeException("进制数超出范围，base<=16");
        }
        StringBuffer str = new StringBuffer("");
        String digths = "0123456789ABCDEF";
        Stack<Character> s = new Stack<Character>();
        while(num != 0){
            s.push(digths.charAt(num%base));
            num/=base;
        }
        while(!s.isEmpty()){
            str.append(s.pop());
        }
        return str.toString();
    }

}
