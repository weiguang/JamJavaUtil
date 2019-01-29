package com.okayjam.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.User;
import com.util.DatabaseUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Chen weiguang <weiguangchen@sf-express.com>
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
}
