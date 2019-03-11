package com.okayjam.designpattern.observer;

/**
 * @author: Chen weiguang <chen2621978@gmail.com>
 * @create: 2019/03/11 17:40
 **/
import java.util.Observable;
import java.util.Observer;

public class AObserver implements Observer {

    public AObserver(ServerManager sm) {
        super();
        // TODO Auto-generated constructor stub
        //注册加入观察者
        sm.addObserver(this);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        if(arg1.equals("data")) {
            System.out.println(arg1);
            System.out.println("AObserver receive:Data has changed to "+((ServerManager) arg0).getData());
        }


    }

}