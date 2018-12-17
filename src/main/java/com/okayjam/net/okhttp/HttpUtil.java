package com.okayjam.net.okhttp;

import okhttp3.*;

import java.io.IOException;

/**
 * @description: HTTP一些工具实现
 * @author: Chen wei guang <weiguangchen@sf-express.com>
 * @create: 2018/07/24  15:17
 **/

public class HttpUtil {

    /**
    * @Description: GET 请求方法
    * @param url
    * @throws IOException
    * @Date: 2018/7/24
    */
    public static void getString(String url) throws IOException {
        // String host = "http://127.0.0.1:8080?Jam=yes";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = client.newCall(request);

        //同步调用,返回Response,会抛出IO异常
        Response response = call.execute();
        System.out.println("Response:" + response.message());

//        //异步调用,并设置回调函数
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                //Toast.makeText(OkHttpActivity.this, "get failed", Toast.LENGTH_SHORT).show();
//                System.out.println("get failed");
//            }
//            @Override
//            public void onResponse(Call call, final Response response) throws IOException {
//                final String res = response.body().string();
//                System.out.println("Response:"+ res);
//            }
//        });
    }

    /**
     * 针对http post 传输json数据处理
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public static String postJson(String url, String json) throws IOException {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
//        //同步
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            System.out.println(response);
        }
//        //异步
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                System.out.println("get failed");
//                System.out.println("Failure");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String string = response.body().string();
//                System.out.println("Response:" + string);
//            }
//        });
        return null;
    }

    public static void postString(String host, String content) {
        //String host = "http://127.0.0.1:8080";
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("username", "admin")
                .add("password", "admin")
                .build();
        // RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), content);

        final Request request = new Request.Builder()
                .url(host)
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Post Failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();
                System.out.println("onResponse:" + res);
            }
        });
    }

}
