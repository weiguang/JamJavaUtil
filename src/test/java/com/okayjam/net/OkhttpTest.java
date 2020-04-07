package com.okayjam.net;


import com.okayjam.net.okhttp.OKHttpUtil;
import com.okayjam.util.DownloadFileUtil;
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

    @Test
    public void testRequest() throws IOException, InterruptedException {
        String url = "https://www.baidu.com";
//        String url = "http://127.0.0.1/index.html";
        OKHttpUtil.requertAsync(url, DownloadFileUtil.HTTP_REQUEST_METHOD_POST, null, null);
        Thread.sleep(2000);
        System.out.println(OKHttpUtil.s);
    }



}
