package com.okayjam.baseTest;

import com.okayjam.test.Animal;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/7 15:01.
 */
public class Reflection {
    public int a = 1;
    int b = 2;
    Object o = new Object();

    @Test
    public void testClass() {
        int a = 10;

        Object obj = new Reflection();

        // 第一種方法
        Class re1 = Reflection.class;

        //第二种方法
        Class re2 = obj.getClass();


        Class re3;
        try {
            // 第三种方法
            re3 = Class.forName("com.okayjam.baseTest.Reflection");
        }catch (Exception e) {
            e.printStackTrace();
        }

        Class<?> re = re1;

        //利用Class 的 newInstance() 生成对象
        try {
            Reflection obj2 = (Reflection)re.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println("package : " + re.getPackage());
        System.out.println(re.getClassLoader());
        for (Constructor<?> constructor : re.getConstructors()) {
            System.out.println(constructor);
        }

        System.out.println("method:");
        for (Method method : re.getMethods()) {
            System.out.println(method);
        }

        System.out.println("declared method:");
        for (Method method : re.getDeclaredMethods()) {
            System.out.println(method);
        }

        System.out.println("Field:");
        for (Field field : re.getFields()) {
            System.out.print(field+", ");
        }

        //invoke test method
        try {
            Method testMethod = re.getMethod("testMethod",int.class);
            testMethod.invoke(obj, a);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void  testMethod() throws Exception{
        Class cls = Class.forName("com.okayjam.test.Animal");
        Object o = cls.getConstructor(String.class).newInstance("JJJJJJJJJJ");
        System.out.println(o);
        Field field = cls.getDeclaredField("name");
        field.setAccessible(true);
        field.set(o, "llllllllllll");
        System.out.println(o);
    }



}
