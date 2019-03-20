package com.okayjam.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.okayjam.test.User;
import com.okayjam.util.DatabaseUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Chen weiguang <chen2621978@gmail.com>
 * @create: 2019/01/16 20:58
 **/

public class DatabaseUtilTest {
    @Test
    public void insert() {
        User user = new User();
        user.setName(null);
        user.setAge(12);
        user.setBirthday(new Date());
        user.setWeight(65.3);
        Object o = JSON.toJSON(user);
        System.out.println(o);
        System.out.println(o instanceof  JSONObject);
        JSONObject o1 = (JSONObject) o;
        List list =new ArrayList();
        list.add(JSON.toJSON(user));
        String jsonFields[] = {"name", "age", "birthday", "weight"};
        String tableFields[] ={"name", "age", "birthday", "weight"};
        String table = "jam1";

        DatabaseUtil.insert(list,jsonFields, tableFields, table);
    }

    @Test
    public void update() {
        JSONObject jsonObject = new JSONObject();
        String jsonFields[] = {"name","weight","birthday","age"};
        String table = "jam1";
        String tableFields[] = {"name","weight","birthday","age"};
        String whereSql = "\t id = 1 \t" ;

        jsonObject.put("name", "jamjam");
        //时间转字符串分钟
        jsonObject.put("weight", 66.777);
        jsonObject.put("birthday", new Date());
        jsonObject.put("age", 99);

        long before = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            DatabaseUtil.update(jsonObject, jsonFields,table, tableFields, whereSql);
        }
        System.out.println(System.currentTimeMillis() - before);

    }
}
