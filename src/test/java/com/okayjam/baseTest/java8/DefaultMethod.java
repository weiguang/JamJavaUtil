package com.okayjam.baseTest.java8;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2020/01/06 10:52
 **/
public class DefaultMethod {
}

interface A  {
    default void hello() {
        System.out.println("Hello from A !");
    }
}

//interface B extends A {
//    default void hello() {
//        System.out.println("Hello from B !");
//    }
//}
//
//interface C implements B,A {
//    public static void main(String[] args) {
//       new C().hello();
//    }
//}


interface B  extends A { }
interface D  extends A { }

class C implements  B, D {
    public static void main(String[] args) {
        new C().hello();
    }

//    @Override
//    public void hello() {
//        B.super.hello();
//    }
}