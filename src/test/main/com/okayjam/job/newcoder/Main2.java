package main.com.okayjam.job.newcoder;

import java.util.Scanner;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/8/1 21:22.
 */
public class Main2 {

    public String multiply(String num1, String num2){
        //把字符串转换成char数组
        char a1[] = num1.toCharArray();
        char a2[] = num2.toCharArray();

        //声明存放结果和两个乘积的容器
        int result[] = new int[a1.length + a2.length];
        int n1[] = new int[a1.length];
        int n2[] = new int[a2.length];

        for(int i = 0; i < a1.length;i++)
            n1[i] = a1[i]-'0';
        for(int i = 0; i < a2.length;i++)
            n2[i] = a2[i]-'0';

        for(int i =0 ; i < a1.length; i++){
            for(int j =0; j < a2.length; j++){
                result[i+j]+=n1[i]*n2[j];
            }
        }

        for(int i =result.length-1; i > 0 ;i--){
            result[i-1] += result[i] / 10;
            result[i] = result[i] % 10;
        }

        String resultStr = "";
        for(int i = 0; i < result.length-1; i++){
            resultStr+=""+result[i];
        }
        return resultStr;
    }

    public static void main(String[] args) {
        Main2 m2 = new Main2();
        Scanner scanner = new Scanner(System.in);
        String a = scanner.next();
        String b = scanner.next();
        String result = m2.multiply(a, b);
        System.out.println(result);
        scanner.close();
    }
}