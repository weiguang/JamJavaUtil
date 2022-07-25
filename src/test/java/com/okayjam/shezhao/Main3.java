package com.okayjam.shezhao;

import java.util.Scanner;
import java.util.Set;

public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] solution = new Main3().solution("oj.abchina.abc", 'a');
        for (int i = 0; i < solution.length; i++) {
            System.out.println(solution[i]);
        }

    }

    public int[] solution(String str, char c) {
        int [] re = new int[str.length()];
        int per = -200001;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != c) {
                re[i] = i - per ;
                continue;
            }
            for (int j = i ; j >= 0 ; j--) {
                int dis = i - j ;
                if (re[j] > dis || re[j] == 0) {
                    re[j] = dis;
                } else {
                    break;
                }
            }
            per = i;
        }
        return re;
    }




}