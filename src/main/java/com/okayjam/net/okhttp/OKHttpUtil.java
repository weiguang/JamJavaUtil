package com.okayjam.net.okhttp;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description: HTTP一些工具实现
 * @author: Chen wei guang <weiguangchen@sf-express.com>
 * @create: 2018/07/24  15:17
 **/

public class OKHttpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(OKHttpUtil.class);

    private static final int HTTP_REQUEST_TIMEOUT = 10;
    public static final String HTTP_REQUEST_METHOD_GET = "GET";
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public static String s;


    static OkHttpClient client;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.SECONDS);
        builder.followRedirects(true);
        client =  builder.build();
    }


    public static OkHttpClient getClient() {
        return client;
    }


    public static Call getConnection(String url, String requestMethod, String headers, String params) throws IOException {
        Request.Builder reqBuilder = new Request.Builder().url(url);

        if (headers != null) {
            Map object = JSONObject.parseObject(headers);
            reqBuilder.headers( Headers.of(object));
            //object.keySet().forEach(re -> reqBuilder.addHeader(re, object.getString(re)));
        }

        RequestBody body = null;
        if (params != null) {
            body = RequestBody.create( params, JSON);
        } else {
            body = RequestBody.create(  new byte[0],null);
        }

        // set default method
        if (requestMethod == null || HTTP_REQUEST_METHOD_GET.equals(requestMethod)) {
            requestMethod = HTTP_REQUEST_METHOD_GET;
            body = null;
        }

        reqBuilder.method(requestMethod, body);

        Call call = getClient().newCall(reqBuilder.build());

        return call;
    }

    public static Response requert(String url, String method, String headers, String params) throws IOException {
        Call conn = getConnection(url, method, headers, params);
        return conn.execute();
    }

    public static void requertAsync(String url, String method, String headers, String params) throws IOException {
        Call conn = getConnection(url, method, headers, params);
            conn.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LOGGER.info("get failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("11111111111111");
                OKHttpUtil.s = response.body().string();
                LOGGER.info("Response:"+ response.body().string());
            }
        });

    }



}
