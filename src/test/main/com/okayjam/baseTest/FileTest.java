package main.com.okayjam.baseTest;

import com.util.FileUtil;
import org.junit.Test;

/**
 * @description: ${description}
 * @author: Chen wei guang <weiguangchen@sf-express.com>
 * @create: 2018/08/09 14:51
 **/
public class FileTest {
    @Test
    public void testDel() throws Exception {
        while (true) {
        String line = FileUtil.delFirstLine("E:\\workspace\\python\\InternetOfThings", "datacurrent.txt");
        if (line == null) {
            System.out.println("文件已经结束，无内容返回！");
            Thread.sleep(3000);
        } else {
            System.out.println(line);
            Thread.sleep(10);
        }
    }
}
}
