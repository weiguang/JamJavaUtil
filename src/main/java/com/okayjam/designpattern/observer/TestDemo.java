package com.okayjam.designpattern.observer;

/**
 * @author: Chen weiguang <chen2621978@gmail.com>
 * @create: 2019/03/11 17:41
 **/
public class TestDemo {
    public static void main(String[] args) {
//        System.out.println( (double) Math.round(1.256 * 100) / 100);
        // TODO Auto-generated method stub
        ServerManager sm = new ServerManager();
        AObserver a    = new AObserver(sm);
        BObserver b = new BObserver(sm);
        sm.setData(5);
        sm.setTemperature(99);
        //注销观察者，以后被观察者有数据变化就不再通知这个已注销的观察者
        sm.deleteObserver(a);
        sm.setData(10);
        sm.setTemperature(100);
    }
}
