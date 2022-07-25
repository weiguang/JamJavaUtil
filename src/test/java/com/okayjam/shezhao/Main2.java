package com.okayjam.shezhao;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(  new Main2().solution("wxxwyzzzw"));

    }

    public String solution(String s) {
        boolean flag = true;
        String str = s;
        StringBuilder sb ;
        while(str.length() > 0 && flag) {
            flag = false;
            sb = new StringBuilder(str.length());
            for (int i = 0; i < str.length() ; i++) {
                if (i == str.length() - 1) {
                    sb.append(str.charAt(i));
                    break;
                }
                if (str.charAt(i) == str.charAt(i+1)) {
                    flag = true;
                    if (i == str.length() - 2) {
                        break;
                    }
                    i++;
                }else {
                    sb.append(str.charAt(i));
                }
            }
            str = sb.toString();

        }
       return str;
    }


}