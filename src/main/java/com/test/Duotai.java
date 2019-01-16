package com.test;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/7/31 17:24.
 */
public class Duotai {
    /**
     * will print three row: Derived
     * @param args
     */
    public static void main(String[] args) {
        Base base = new Derived();
        base.echo();
    }
}


class Base {
    Base() {echo();}
     void echo() {
         System.out.println("Base");}
};

class Derived extends Base {
        Derived() {echo();}
        void echo() {
            System.out.println("Derived");}
};

