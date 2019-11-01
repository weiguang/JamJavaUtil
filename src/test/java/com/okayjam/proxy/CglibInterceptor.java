package com.okayjam.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2019/09/06 16:03
 **/
public class CglibInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object re = methodProxy.invokeSuper(o, objects);
        return re;
    }
}
