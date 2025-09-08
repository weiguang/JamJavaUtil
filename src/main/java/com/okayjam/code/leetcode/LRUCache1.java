package com.okayjam.code.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * use LinkedList
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2025/09/07 10:28
 **/

public class LRUCache1 extends LinkedHashMap<Integer, Integer> {


    int capacity;
    public LRUCache1(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }

    public int get(int key) {
        Object value = this.get(key);
        if (value == null) {
            return  -1;
        } else {
            return (int) value;
        }
    }

    public void put(int key, int value) {
        this.put(key, value);
    }
}



