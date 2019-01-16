package main.com.okayjam.baseTest;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: Chen weiguang <weiguangchen@sf-express.com>
 * @create: 2018/08/27 15:35
 **/
public class DateTest {
    @Test
    public void test() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);
        String localTime = df.format(time);
        LocalDateTime ldt = LocalDateTime.parse("2017-09-28 17:07:05", df);
        System.out.println("LocalDateTime转成String类型的时间：" + localTime);
        System.out.println("String类型的时间转成LocalDateTime：" + ldt);
        java.time.Duration.between(time, time);
    }

}
