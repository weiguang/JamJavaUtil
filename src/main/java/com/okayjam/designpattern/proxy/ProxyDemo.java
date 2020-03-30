package com.okayjam.designpattern.proxy;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.regex.Pattern;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2019/09/06 15:05
 **/
public class ProxyDemo {
    public static void main(String[] args)  {
        jdkProxyDemo();
    }

    /**
     * 复用代理
     * @param inf 接口的类
     * @param realObj 具体实现的对象
     * @param <T> 类型
     * @return 返回代理对象
     */

    private static<T> T getProxy(Class<T> inf, T realObj) {
        return (T) Proxy.newProxyInstance(inf.getClassLoader(), new Class<?>[] {inf}, new InvoHandler(realObj));
    }

    static void jdkProxyDemo() {
        IService proxyService = getProxy(IService.class, new IServiceImpl());
        proxyService.sayHello();
        proxyService.sayHello2();
        proxyService.toString();


        IService2 proxyService2 = getProxy(IService2.class, new IService2Impl());
        proxyService2.fly();

    }

    static void jdkProxyDemo2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> proxyCls = Proxy.getProxyClass(IService.class.getClassLoader(), IService.class);
        Constructor constructor = proxyCls.getConstructor(InvocationHandler.class);
        IService proxyService = (IService) constructor.newInstance(new InvoHandler(new IServiceImpl()));
        proxyService.sayHello();
    }


    static void cglibDemo() {
        IServiceImpl proxyService = getProxy(IServiceImpl.class);
        proxyService.sayHello();
        System.out.println("是否是代理：" + Proxy.isProxyClass(proxyService.getClass()));
        System.out.println("是否是代理：" + Proxy.isProxyClass(new IServiceImpl().getClass()));
    }

    /**
     * cglib 代理
     * @param inf
     * @param <T>
     * @return
     */
    private static<T> T getProxy(Class<T> inf) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(inf);
        enhancer.setCallback(new CglibInterceptor());
        return (T) enhancer.create();
    }
}
