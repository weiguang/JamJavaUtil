package com.okayjam.proxy;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2019/09/06 15:01
 **/
public class IServiceImpl implements IService{
    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }

    @Override
    public void sayHello2() {
        System.out.println("Hello World2!");
    }
}
