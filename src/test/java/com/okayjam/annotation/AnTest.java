package com.okayjam.annotation;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2019/09/06 11:30
 **/
public class AnTest {
    @Test
    public void  t() throws Exception{
        Class cls = Class.forName("com.okayjam.test.User");
        Object o = cls.newInstance();
        System.out.println(o);
        Field field = cls.getDeclaredField("name");
        field.setAccessible(true);
        JamAnnotation j = field.getAnnotation(JamAnnotation.class);
        if (j != null) {
            field.set(o, j.value());
        }
        System.out.println(o);
        field.set(o, "jjjjjjjjjjjj");
        System.out.println(o);
    }
}
