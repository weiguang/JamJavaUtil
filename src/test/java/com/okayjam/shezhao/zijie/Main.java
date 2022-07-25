package com.okayjam.shezhao.zijie;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        SingleTon s = SingleTon.getInstance();
    }

}

class SingleTon {
    private SingleTon() {}
    private static volatile SingleTon INSTANCE = null;
    public static SingleTon getInstance() {
        if (INSTANCE == null ) {
            synchronized(SingleTon.class) {
                if ( INSTANCE == null ) {
                    INSTANCE = new SingleTon();
                }
            }
        }
        return INSTANCE;
    }
}