package com.okayjam.code.leetcode.solution;

import java.util.LinkedList;

/**
 * 705. 设计哈希集合
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 *
 * @author JamChen jamchen@tencent.com
 * @date 2026/10/18 20:55
 **/
public class MyHashSet {

    /*
    private final boolean[] keys;

    public MyHashSet() {
        keys = new boolean[1000001];
    }

    public void add(int key) {
        keys[key] = true;
    }

    public void remove(int key) {
        keys[key] = false;
    }

    public boolean contains(int key) {
        return keys[key];
    }
     */

    private static final int BASE = 769; // 取质数作为桶数量，减少冲突
    private final LinkedList<Integer>[] buckets;

    @SuppressWarnings("unchecked")
    public MyHashSet() {
        buckets = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return key % BASE;
    }

    public void add(int key) {
        int h = hash(key);
        for (int element : buckets[h]) {
            if (element == key) {
                return;
            }
        }
        buckets[h].addLast(key);
    }

    public void remove(int key) {
        int h = hash(key);
        for (Integer element : buckets[h]) {
            if (element == key) {
                buckets[h].remove(element);
                return;
            }
        }
    }

    public boolean contains(int key) {
        int h = hash(key);
        for (int element : buckets[h]) {
            if (element == key) {
                return true;
            }
        }
        return false;
    }

}
