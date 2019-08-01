package com.okayjam.beanUtils;

import com.okayjam.test.User;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author: Chen weiguang <chen2621978@gmail.com>
 * @create: 2019/07/26 16:13
 **/
public class CommonsBeanutils {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        User p = new User();
        //使用beanUtils给对象的属性赋值
        BeanUtils.setProperty(p, "name", "张三");
        //使用beanUtils获取对象的属性值
        System.out.println(BeanUtils.getProperty(p, "name"));

        User p2 = new User();
        BeanUtils.copyProperties(p2, p);
        System.out.println(BeanUtils.getProperty(p2, "name"));


        PropertyUtils.copyProperties(p2, p);
        System.out.println(BeanUtils.getProperty(p2, "name"));

        cgLib();

    }



    public static  void cgLib() {
        User p = new User();
        User p2 = new User();
        p.setName("张33333333333");
        BeanCopier copier = BeanCopier.create(User.class, User.class, false);

        copier.copy(p, p2, null);

        System.out.println(p2.getName());

    }


}
