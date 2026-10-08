package com.okayjam.code.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 677. 键值映射
 * 前缀树（Trie）+ 增量权重/DFS
 *  Map 保存当前键值：因为调用 insert 时如果 key 已存在，需要用新值覆盖旧值。
 *  我们需要知道 delta = newVal - oldVal。
 * Trie 节点保存 val：Trie 节点的 val 代表以该节点为前缀的所有 key 的权值总和。
 *
 * @author JamChen jamchen@tencent.com
 * @date 2026/10/08 15:34
 **/
public class MapSum {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        // 记录以当前节点为前缀的所有 key 的 value 总和
        int val = 0;
    }

    private final TrieNode root;
    private final Map<String, Integer> map;

    public MapSum() {
        root = new TrieNode();
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        // 计算增量：如果是更新 key，只需加上差值 delta
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);

        TrieNode node = root;
        for (char c : key.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
            node.val += delta; // 沿途更新前缀和
        }
    }

    public int sum(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return 0; // 前缀不存在
            }
            node = node.children[index];
        }
        return node.val; // 直接读取保存的前缀和，O(L) 复杂度
    }

}
