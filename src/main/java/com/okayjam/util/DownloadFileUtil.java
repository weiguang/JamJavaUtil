package com.okayjam.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2020/03/10 20:18
 **/
public class DownloadFileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DownloadFileUtil.class);

    private static final int HTTP_REQUEST_TIMEOUT = 20000;
    public static final String HTTP_REQUEST_METHOD_GET = "GET";
    public static final String HTTP_REQUEST_METHOD_POST = "POST";

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
        HttpURLConnection conn = getConnection(downloadUrl, requestMethod, params, headers);
        int responseCode = conn.getResponseCode();
        if(responseCode != HttpURLConnection.HTTP_OK){
            LOGGER.error("【下载导出文件】请求响应错误！返回状态码：{} ，错误信息：{} ", responseCode, getErrorInfo(conn));
            return null;
        }
        // 获取文件名
        String field = conn.getHeaderField("Content-Disposition");
        String fileName ;
        if (field != null) {
            fileName = URLDecoder.decode(field, String.valueOf(StandardCharsets.UTF_8));
            fileName = fileName.replaceFirst("(?i)^.*filename=\"?([^\"]+)\"?.*$", "$1");
        } else  {
            fileName = conn.getURL().getHost() + "-" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
            String extension = FilenameUtils.getExtension(conn.getURL().getPath());
            if (extension != null) {
                fileName += "." + extension;
            }
        }
        // 读取输入流并保存
        InputStream inStream = new BufferedInputStream(conn.getInputStream());
        String fullPath = path + "/" + fileName;
        saveToFile(inStream, fullPath);
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
    public static HttpURLConnection getConnection(String url, String requestMethod, String params, String headers) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setConnectTimeout(HTTP_REQUEST_TIMEOUT);
        conn.setReadTimeout(HTTP_REQUEST_TIMEOUT);

        // 设置headers
        if (headers != null  && headers.length() > 0) {
            JSONObject header = JSONObject.parseObject(headers);
            for (String key : header.keySet()) {
                conn.setRequestProperty(key, header.getString(key));
            }
        }

        if (HTTP_REQUEST_METHOD_POST.equals(requestMethod)) {
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(HTTP_REQUEST_METHOD_POST);
            if (params != null) {
                OutputStream os = conn.getOutputStream();
                os.write(params.getBytes(StandardCharsets.UTF_8));
                os.close();
            }
        } else {
            conn.setRequestMethod(HTTP_REQUEST_METHOD_GET);
            conn.connect();
        }
        return conn;
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
