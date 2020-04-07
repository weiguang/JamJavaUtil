package com.okayjam.util;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2020/03/10 20:18
 **/
public class DownloadFileUtil2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(DownloadFileUtil2.class);

    private static final int HTTP_REQUEST_TIMEOUT = 10;
    public static final String HTTP_REQUEST_METHOD_GET = "GET";
    public static final String HTTP_REQUEST_METHOD_POST = "POST";
    final static MediaType JSONType = MediaType.parse("application/json; charset=utf-8");

    static OkHttpClient client;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.SECONDS);
        builder.followRedirects(true);
         client =  builder.build();
    }

    public static String download(String downloadUrl, String requestMethod, String params, String path) throws IOException {
        return download(downloadUrl, requestMethod, params, null, path);
    }

    /**
     *  eg.    DownloadFileUtil.download(url, "POST", params, "d:\1\");
     * @param downloadUrl 下载地址
     * @param requestMethod 请求方式。 POST 或者 GET
     * @param params 如果是POST，还有请求参数，json格式
     * @param headers header参数，json格式
     * @param path 保存文件的目录（是目录，文件名会根据返回流得到）
     * @return 返回最终文件保存的路径
     * @throws IOException IO异常
     */
    public static String download(String downloadUrl, String requestMethod, String params, String headers, String path) throws IOException {
        Call conn = getConnection(downloadUrl, requestMethod, params, headers);
        Response response = conn.execute();
        int responseCode = response.code();
        if(responseCode != HttpURLConnection.HTTP_OK){
            LOGGER.error("【下载导出文件】请求响应错误！返回状态码：{} ，错误信息：{}, {} ", responseCode, response.message() ,
                    response.body() != null ? response.body().string() : null);
            return null;
        }
        // 获取文件名
        String field = response.header("Content-Disposition");
        String fileName ;
        if (field != null) {
            fileName = URLDecoder.decode(field, String.valueOf(StandardCharsets.UTF_8));
            fileName = fileName.replaceFirst("(?i)^.*filename=\"?([^\"]+)\"?.*$", "$1");
        } else  {
            fileName = conn.request().url().url().getHost() + "-" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
            String extension = FilenameUtils.getExtension(conn.request().url().url().getPath());
            if (extension != null) {
                fileName += "." + extension;
            }
        }
        // 读取输入流并保存
        InputStream inStream = new BufferedInputStream(response.body().byteStream());
        String fullPath = path + "/" + fileName;
        saveToFile(inStream, fullPath);
        response.close();
        return fullPath;
    }

    /**
     * 获取请求
     * @param url 地址
     * @param requestMethod 请求方式。 POST 或者 GET
     * @param params 如果是POST，还有请求参数，json格式
     * @return 返回 HttpURLConnection
     * @throws IOException 异常
     */
    public static Call getConnection(String url, String requestMethod, String params, String headers) throws IOException {
        Request.Builder reqBuilder = new Request.Builder().url(url);

        if (headers != null) {
            JSONObject object = JSONObject.parseObject(headers);
            object.keySet().forEach(re -> reqBuilder.addHeader(re, object.getString(re)));
        }

        RequestBody body = null;
        if (params != null) {
             body = RequestBody.create(JSONType, params);
        }

        // set default method
        if (requestMethod == null) {
            requestMethod = HTTP_REQUEST_METHOD_GET;
        }
        reqBuilder.method(requestMethod, body);

        Call call = client.newCall(reqBuilder.build());

        return call;
    }

    /**
     * 保存文件
     * @param inStream 流
     * @param path 保存文件的路径
     * @throws IOException IO
     */
    public static void saveToFile(InputStream inStream, String path) throws IOException {
        FileOutputStream fs = new FileOutputStream(path);
        // 下载网络文件
        int byteread ;
        byte[] buffer = new byte[4096];
        while ((byteread = inStream.read(buffer)) != -1) {
            fs.write(buffer, 0, byteread);
        }
        fs.close();
    }


    /**
     * 获取错误信息
     * @param conn 链接
     * @return 错误信息
     */
    private static String getErrorInfo(HttpURLConnection conn) {
        InputStream is = new BufferedInputStream( conn.getErrorStream());
        BufferedReader br ;
        String result = null;
        try {
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sbf = new StringBuffer();
            String temp = null;
            // 循环遍历一行一行读取数据
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }
            result = sbf.toString();
        } catch (Exception e) {
            LOGGER.error("[下载文件]获取错误信息异常");
        }
        return result;
    }

}
