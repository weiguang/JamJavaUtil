package com.okayjam.code.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2025/09/30 22:12
 **/
public class Codec {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Default");
        list.add("jam");
        String encode = new Codec().encode(list);
        System.out.println(encode);
        new Codec().decode(encode).forEach(System.out::println);
    }
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append(',').append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while(i < s.length()) {
            int cur = i;
            while(s.charAt(i) != ',') {
                i++;
            }
            int n = Integer.parseInt(s.substring(cur, i));
            i++;
            if (n != 0) {
               res.add(s.substring(i, i+n));
            } else {
                res.add("");
            }
            i+=n;
        }
        return res;
    }
}
