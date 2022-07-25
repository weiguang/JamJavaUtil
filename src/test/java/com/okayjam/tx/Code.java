package com.okayjam.tx;

import org.apache.commons.codec.binary.Base64;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2020/10/21 09:54
 **/
public class Code {
    public static void main(String[] args) throws IOException {
        System.out.println("Default main method!");
        m2();
    }

    public static void m1(){
        int[] bag = {1376, 4257, 3746, 3592, 1136,2992
                    ,3536,2832,4531, 2227, 3475, 3189
                    ,1813,4693,2454, 3126, 2966,2200
                    ,3673, 2970, 2811, 3582, 1823, 2303};
        int max = 5000;
        List<Integer> reList = new ArrayList<>();
        int curMax = 0;
        for (int i = 0; i < bag.length ; i++) {
            if (bag[i] > max) {continue;}
            List<Integer> tList = new ArrayList<>();
            tList.add(bag[i]);
            if (tList.stream().mapToInt(r->r).sum() > curMax) {
                reList = new ArrayList<>(tList);
                curMax = tList.stream().mapToInt(r->r).sum();
            }
            for (int j = i + 1; j < bag.length; j++) {
                if (tList.stream().mapToInt(r->r).sum() + bag[j] > max) {continue;}
                tList.add(bag[j]);
                if (tList.stream().mapToInt(r->r).sum() > curMax) {
                    reList = new ArrayList<>(tList);
                    curMax = tList.stream().mapToInt(r->r).sum();
                }
                for (int k = j + 1; k < bag.length; k++) {
                    if (tList.stream().mapToInt(r->r).sum() + bag[k] > max) {continue;}
                    tList.add(bag[k]);
                    if (tList.stream().mapToInt(r->r).sum() > curMax) {
                        reList = new ArrayList<>(tList);
                        curMax = tList.stream().mapToInt(r->r).sum();
                    }
                    tList.remove(tList.size() - 1);
                }
                tList.remove(tList.size() - 1);
            }
        }
        System.out.println(reList.stream().map(re->re+"").collect(Collectors.joining(", ")));
    }

    public static void m2() throws IOException {
        String str = "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";

    byte[] bytes= Base64.decodeBase64(str);
        for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                        bytes[i] += 256;
                } else if (bytes[i] > 256) {
                    bytes[i] -= 256;
                } else {

                }
        }
        OutputStream outputStream=new FileOutputStream("/Users/jam/aaa");
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();

    }
}
