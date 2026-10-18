package com.okayjam.code.leetcode.solution;

/**
 * 706. 设计哈希映射
 * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 *
 * @author JamChen jamchen@tencent.com
 * @date 2026/10/18 21:12
 **/
public class MyHashMap {
    private static class Node {
        int key, value;
        Node next;

        Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private static final int BASE = 769;
    private final Node[] buckets;

    public MyHashMap() {
        // Java 默认将数组初始化为 null，耗时 O(1)，0 对象创建开销
        buckets = new Node[BASE];
    }

    private int hash(int key) {
        return key % BASE;
    }

    public void put(int key, int value) {
        int h = hash(key);
        Node cur = buckets[h];
        while (cur != null) {
            if (cur.key == key) {
                cur.value = value;
                return;
            }
            cur = cur.next;
        }
        // 头插法：直接把新节点作为头节点挂上去，旧头节点变 next
        buckets[h] = new Node(key, value, buckets[h]);
    }

    public int get(int key) {
        int h = hash(key);
        Node cur = buckets[h];
        while (cur != null) {
            if (cur.key == key) {
                return cur.value;
            }
            cur = cur.next;
        }
        return -1;
    }

    public void remove(int key) {
        int h = hash(key);
        Node cur = buckets[h];
        if (cur == null) return;

        // 特殊处理：如果要删除的是头节点
        if (cur.key == key) {
            buckets[h] = cur.next;
            return;
        }

        // 删除非头节点
        while (cur.next != null) {
            if (cur.next.key == key) {
                cur.next = cur.next.next;
                return;
            }
            cur = cur.next;
        }
    }
}
