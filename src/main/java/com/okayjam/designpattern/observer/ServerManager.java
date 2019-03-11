package com.okayjam.designpattern.observer;

/**
 * @author: Chen weiguang <chen2621978@gmail.com>
 * @create: 2019/03/11 17:40
 **/
import java.util.Observable;

public class ServerManager extends Observable {
    private int data = 0;
    private double temperature;
    private String status;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        if(this.temperature != temperature) {
            this.temperature = temperature; setChanged();
        }
        notifyObservers("temperature");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getData(){         return data;    }
    public void setData(int i){
        if(this.data != i){ this.data = i;setChanged();}
        notifyObservers("data");  //只有在setChange()被调用后，notifyObservers()才会去调用update()，否则什么都不干。  } }
    }

}