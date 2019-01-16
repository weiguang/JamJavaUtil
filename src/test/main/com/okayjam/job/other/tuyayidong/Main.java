package main.com.okayjam.job.other.tuyayidong;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/26 8:28.
 */
public class Main {
    static int N = 2;
    public static void main (String[] args) {
        //func(2017,N, 17);
        String[] regionArry = {"110101","110102"};
        System.out.println(verify("110101197204300849", regionArry));
    }
    /**
     * 验证身份证号码
     * @param idCard 居民身份证号码18位，最后一位可能是数字或字母x
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIdCard(String idCard) {
        String regex = "[1-9]\\d{13,16}[x|0-9]{1}";
        return Pattern.matches(regex,idCard);
    }

    public static String verify(String id, String[] regionArry) {
        //判断基本的格式
        if (id != null && checkIdCard(id)) {
            String regionCode = id.substring(0, 6);
            // 校验地区
            int i =0;
            for ( i= 0; i <regionArry.length; i++) {
                if(regionCode.equals(regionArry[i])) break;
            }
            if(i == regionArry.length) return "false";
            //校验出生日期
            String birthday = id.substring(6, 14);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            try {
                Date bDate = sdf.parse(birthday);
                String tDate = sdf.format(bDate);
                if (!tDate.equals(birthday)) {
                    return "false";
                }
            } catch (ParseException e1) {
                return "false";
            }
            //随机数不能等于0
            if(id.substring(14,17).equals("000")) return "false";
            //检验位
            String idCode = id.substring(0, 17);
            char vCode = id.charAt(17);
            char[] c = idCode.toCharArray();
            double sum = (vCode == 'x') ? 10 : vCode-'0';
            for (i = 0; i < 17; i++) {
                sum += c[i] * Math.pow(2,17 - i);
            }
            if(sum % 11 != 1 ) return "false";
            if(Integer.valueOf(id.substring(14,17)) % 2 == 1 ) return "Male";
            else return "Female";

        }else{
            return "false";
        }
    }
}
