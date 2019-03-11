package com.okayjam.designpattern.observer;

/**
 * @author: Chen weiguang <chen2621978@gmail.com>
 * @create: 2019/03/11 17:41
 **/
import java.util.Observable;
import java.util.Observer;

public class BObserver implements Observer {

    public BObserver(ServerManager sm) {
        super();
        //注册加入观察者
        sm.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        if(arg.equals("temperature")) {
            System.out.println(arg);
            System.out.println("BObserver receive:Data has changed to "+((ServerManager) o).getTemperature());
        }

    }

}
