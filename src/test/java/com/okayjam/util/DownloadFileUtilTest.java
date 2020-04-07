package com.okayjam.util;


import com.okayjam.net.okhttp.OKHttpUtil;
import org.junit.Test;
import java.io.IOException;


/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2020/03/30 14:10
 **/
public class DownloadFileUtilTest {

    @Test
    public void download() throws IOException {
        String url = "https://www.baidu.com/index.html";
//        String url = "http://127.0.0.1/index.html";
/*        long start = System.currentTimeMillis();
        DownloadFileUtil2.download(url, DownloadFileUtil.HTTP_REQUEST_METHOD_GET, null, "d:/1");
        System.out.println( System.currentTimeMillis() - start );*/

        OKHttpUtil.requertAsync(url, DownloadFileUtil.HTTP_REQUEST_METHOD_GET, null, null);
    }


}