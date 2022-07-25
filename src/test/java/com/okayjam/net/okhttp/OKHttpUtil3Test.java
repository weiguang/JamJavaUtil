package com.okayjam.net.okhttp;

import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2021/07/21 20:23
 **/
class OKHttpUtil3Test {

    @Test
    void requert() throws IOException {
        String url = "https://baidu.com";
        String text = "测试数据！";
        Map<String, String> header = new HashMap<>();
        header.put("Connection", "Keep-Alive");
        Map<String, String> params = new HashMap<>();
        header.put("Connection", "Keep-Alive");
        Response request = OKHttpUtil3.post(url, header, null);
    }

    @Test
    void requertAsync() {
    }

    @Test
    public void test() throws IOException {
        String webhook = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=5058cbb9-c904-4c05-a291-742820bdb1d9";
        Map<String, Object> params = new HashMap<>();
        params.put("msgtype", "text");

        Map<String, Object> content = new HashMap<>();
        content.put("content", "Hi，我是机器人对账管理台测试\\n由jamchen于07月20日添加到群");
        params.put("text", content);
        OKHttpUtil3.post(webhook,null, params);
    }

    @Test
    public void testEmail() throws IOException {
        String emailApi = "http://mail.midas.oa.com/send/sendApi";
        Map<String, Object> params = new HashMap<>();
        params.put("cc", "jamchen");
        params.put("receiver", "jamchen");
        params.put("title", "测试邮件");
        params.put("content", "just test");
        Response response = OKHttpUtil3.postForm(emailApi, null, params);
        System.out.println(response.body().string());
    }
}