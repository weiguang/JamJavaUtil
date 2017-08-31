package other.baicikan;

import java.util.Scanner;
import java.util.regex.Pattern;
/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/31 10:02.
 */
public class Test {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        while (sn.hasNextLine()) {
            String s = sn.nextLine();
            String regex = "^[a|b|1-8]+[a|b]+$";
            if (!Pattern.matches(regex,s)) System.out.println("格式错误");
            else System.out.println(getIpAddress(s)); //7ab7ab7a2b6ab
        }
    }

    private static String longToIP(long longIp) {
        StringBuilder sb = new StringBuilder("");
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }

    private static String string2binary(String str) {
        if(str == null || str.length() == 0 ) throw new IllegalArgumentException("Error:Input String can not null or empty!");
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                sb.append(getChar(str, i));
            } else {
                for (int j = 0; j < str.charAt(i) - '0'; j++) {
                    sb.append(getChar(str, i + 1));
                }i++;
            }
        }
        return sb.toString();
    }

    private static int getChar(String str, int i) { return str.charAt(i) - 'a' ; }

    private static String getIpAddress(String input) {
        try {
            return longToIP(Long.valueOf(string2binary(input), 2));
        } catch (NumberFormatException e) { return "输入格式错误"; }
    }
}

