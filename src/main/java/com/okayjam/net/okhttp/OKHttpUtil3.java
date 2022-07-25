package com.okayjam.net.okhttp;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import okio.BufferedSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @description: HTTP一些工具实现
 * @author: Chen wei guang <weiguangchen@sf-express.com>
 * @create: 2018/07/24  15:17
 **/

public class OKHttpUtil3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(OKHttpUtil3.class);

    private static final int HTTP_REQUEST_TIMEOUT = 10;
    public static final String HTTP_REQUEST_METHOD_GET = "GET";
    public static final String HTTP_REQUEST_METHOD_POST = "POST";

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    public static final MediaType FORM
            = MediaType.get("application/x-www-form-urlencoded; charset=utf-8");


    static OkHttpClient client;

    // 类加载的单例模式
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


    public static Call getConnection(String url, String requestMethod, Map<String, String> headerMap, Map<String, Object> params, Boolean isJson) {
        Request.Builder reqBuilder = new Request.Builder();
        if (headerMap != null) {
            reqBuilder.headers(Headers.of(headerMap));
        }

        RequestBody body ;
        if (params != null) {
            if (isJson) {
                body = RequestBody.create( JSONObject.toJSONString(params), JSON);
            } else {
            body = RequestBody.create( genParamString(params), FORM);
            }
        } else {
            body = RequestBody.create(  new byte[0],null);
        }

        // set default method
        if (requestMethod == null || HTTP_REQUEST_METHOD_GET.equalsIgnoreCase(requestMethod)) {
            requestMethod = HTTP_REQUEST_METHOD_GET;
            body = null;
            if (params != null) {
                url += url.contains("?") ? "&":"?" + genParamString(params);
            }
        }

        reqBuilder.method(requestMethod, body).url(url);

        Call call = getClient().newCall(reqBuilder.build());

        return call;
    }


    /**
     * 基础请求
     * @param url url
     * @param method 请求方法
     * @param headerMap 请求头
     * @param params 参数
     * @param isJson 是否是json
     * @return 返回response
     * @throws IOException 异常
     */
    public static Response request(String url, String method,  Map<String, String> headerMap, Map<String, Object> params, Boolean isJson) throws IOException {
        Call conn = getConnection(url, method, headerMap, params, isJson);
        LOGGER.info("http request" + conn.request());
        Response response = conn.execute();
        BufferedSource source = response.body().source();
        source.request(Long.MAX_VALUE);
        String body = source.getBuffer().clone().readString(StandardCharsets.UTF_8);
        LOGGER.info("response: {}, body:{} " , response, body);
        return response;
    }

    /**
     * get请求
     * @param url 地址
     * @return 返回Response
     * @throws IOException 异常
     */
    public static Response request(String url) throws IOException {
        return get(url, null);
    }

    /**
     * get请求
     * @param url 地址
     * @param headerMap 头函数
     * @return 返回Response
     * @throws IOException 异常
     */
    public static Response get(String url,  Map<String, String> headerMap) throws IOException {
        return request(url, HTTP_REQUEST_METHOD_GET, headerMap, null, false);
    }

    /**
     * post
     * @param url 地址
     * @param headerMap 头函数
     * @param params 参数
     * @return 返回
     * @throws IOException 异常
     */
    public static Response post(String url,  Map<String, String> headerMap, Map<String, Object> params) throws IOException {
        return  request(url, HTTP_REQUEST_METHOD_POST, headerMap, params, true);
    }

    public static Response postForm(String url,  Map<String, String> headerMap, Map<String, Object> params) throws IOException {
       return request(url, HTTP_REQUEST_METHOD_POST, headerMap, params, false);
    }


    public static Response request(String url, String method,  Map<String, String> headerMap, Map<String, Object> params) throws IOException {
        return request(url, method, headerMap, params, true);
    }

    public static void requestAsync(String url, String method,  Map<String, String> headerMap, Map<String, Object> params, Boolean isJson) throws IOException {
        Call conn = getConnection(url, method, headerMap, params, isJson);
            conn.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LOGGER.info("get failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("11111111111111");
                LOGGER.info("Response:"+ response.body().string());
            }
        });

    }

    /**
     * 获取字符串信息
     * @param params
     * @return
     */
    public static String genParamString( Map<String, Object> params){
        if (params == null) {return null;}
        String strParams = params.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
        return strParams;
    }


}
