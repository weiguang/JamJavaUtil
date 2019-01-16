package main.com.okayjam.job.newcoder.huawei;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/10 16:34.
 */
public class Main {
    static Scanner sn = new Scanner(System.in);
    public static void main (String[] args) {
        while (sn.hasNextLine()){
            CalCoordinate(sn.nextLine());
        }

    }

    public static void checkIp() {
        int sum = 0;
        while(sn.hasNextLine()) {
            String[] ip = sn.nextLine().split("~");
            if(isIP(ip[0]) && isIP(ip[1])){

            }else{
                sum++;
            }
        }

    }

    public static boolean isIP(String ip) {
        String regex = "[0-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
        return Pattern.matches(regex, ip);
    }

    public static  boolean checkMask(String mask) {
        StringBuilder sb = new StringBuilder(32);
        for (String s : mask.split(".")) {
            sb.append( Integer.toBinaryString(Integer.valueOf(s)) );
        }
        int flag = 0;
        for (int i = sb.length() - 1; i >= 0 ; i--) {
            if (sb.subSequence(i,i + 1).equals("0")) {
                if(flag == 1) return false;
            }else {
                flag = 1;
            }
        }
        return true;
    }


    public static void CalCoordinate(String src) {
        long x = 0, y = 0;
        String[] st = src.split(";");
        for (String s : st) {
            if(s.length() <2 || s.length() > 3) continue;
            if(s.length() == 2) {
                s = s.charAt(0) + "0" + s.charAt(1);
            }
            char m = s.charAt(0);
            if(Character.isLetter(m) && Character.isDigit(s.charAt(1)) &&  Character.isDigit(s.charAt(2))) {
                int n = Integer.valueOf(s.substring(1));
                switch(m) {
                    case 'A' : x -= n; break;
                    case 'D' : x += n; break;
                    case 'W' : y += n; break;
                    case 'S' : y -= n; break;
                }
            }
        }
        System.out.println(x + "," + y);
    }
}
