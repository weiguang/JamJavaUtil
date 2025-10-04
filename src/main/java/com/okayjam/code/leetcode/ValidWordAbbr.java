package com.okayjam.code.leetcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 288. 单词的唯一缩写
 * <a href="https://leetcode.cn/problems/unique-word-abbreviation">288. 单词的唯一缩写</a>
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2025/10/04 15:43
 **/
public class ValidWordAbbr {

    private Map<String, Set<String>> map;
    public ValidWordAbbr(String[] dictionary) {
        map =  new HashMap<>();
        for (int i = 0; i < dictionary.length; i++) {
            map.computeIfAbsent(abbreviation(dictionary[i]), k ->new HashSet<>()).add(dictionary[i]);
//            map.put(abbreviation(dictionary[i]), dictionary[i]);
        }
    }

    public boolean isUnique(String word) {
        Set<String> set = map.get(abbreviation(word));
        if (set == null || set.size()<2 && set.contains(word)) return true;
        return false;
    }

    private String abbreviation(String word) {
        if (word == null || word.length() < 3) {
            return word;
        }
        return ""+ word.charAt(0) + (word.length() -2) + word.charAt(word.length()-1);
    }
}
