package com.okayjam.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2019/09/06 15:02
 **/
public class InvoHandler implements InvocationHandler {

    Object realObject;

    public InvoHandler(Object realObject) {
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Start invoke");
        Object re = method.invoke(realObject,args);
       return re;
    }
}
