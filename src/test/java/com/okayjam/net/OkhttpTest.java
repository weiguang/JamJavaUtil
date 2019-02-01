package com.okayjam.net;


import com.okayjam.net.okhttp.HttpUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @description: HTTP工具测试，使用Junit测试
 * @author: Chen wei guang <weiguangchen@sf-express.com>
 * @create: 2018/07/24  15:17
 **/
public class OkhttpTest {
    public String host = "";

    @Before
    public void setUp() {
//        host = "http://10.118.12.27:8987/";
        host = "http://127.0.0.1:10001/";
        System.out.println(this.getClass().getName() + " test start!");
    }

    @After
    public void tearDown() {
        System.out.println("Test end!");
    }

//    // test method to add two values
//    @Test
//    public void testAdd(){
//        double result= 2 + 3;
//        Assert.assertTrue(result==5);
//    }

    @Test
    public void testGet() throws IOException {
//        HttpUtil.getString(hots + "?jam=yes");
        HttpUtil.getString(host + "tcp_data");
    }

//    @Test
    public void testPost() throws IOException {
//       HttpUtil.postJson(host,"JamJam");
         HttpUtil.postJson(host + "tcp_data","\"username\":\"7845166313\",\"password\":\"123456\"}");
    }

}
