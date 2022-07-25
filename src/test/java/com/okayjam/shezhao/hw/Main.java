package com.okayjam.shezhao.hw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String str = "IAmTom";
        List<String> list = getWordList(str);
        System.out.println(String.join("", list));
    }

    /**
     * 将句子中的单词分离并翻转
     * @param str
     * @return
     */
    public static List<String> getWordList(String str) {
        if (str == null || str.length() == 0) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < str.length()) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                if (sb.length() > 0) {
                    list.add(sb.reverse().toString());
                    sb = new StringBuilder();
                }
            }
            sb.append(c);
            i++;
        }
        if (sb.length() > 0) {
            list.add(sb.reverse().toString());
        }
        return list;
    }


}