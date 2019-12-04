package com.okayjam.designpattern.proxy.rmi;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2019/12/04 15:48
 **/

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * 1、进入目录：javac -d . *.java
 * 2、运行服务端：java  com.okayjam.designpattern.proxy.rmi.MyRemoteImpl
 * 3、运行客户端：java  com.okayjam.designpattern.proxy.rmi.MyRemoteClient
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote{


    private static final long serialVersionUID = 1L;

    protected MyRemoteImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Server says, 'Hey'";
    }

    public static void main(String[] args) {
        try {

            MyRemote service = new MyRemoteImpl();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("RemoteHello", service);
        } catch(Exception ex) {}
    }

}

