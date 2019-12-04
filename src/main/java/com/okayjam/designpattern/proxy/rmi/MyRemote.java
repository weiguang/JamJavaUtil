package com.okayjam.designpattern.proxy.rmi;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2019/12/04 15:48
 **/

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface MyRemote extends Remote {
    public String sayHello() throws RemoteException;

}
