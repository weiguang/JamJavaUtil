package com.okayjam.shezhao;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double[] solution = new Main().solution(1000000, 7, 0.003959);

    }

    public double[] solution(double p, int n, double r) {
        double re[] = new double[n];
        double per = p / n;
        double remain = p;
        for (int i = 0; i < n; i++) {
            re[i] = per + remain * r;
            remain = remain - per;
            System.out.println(re[i]);
        }
        return re;
    }


}