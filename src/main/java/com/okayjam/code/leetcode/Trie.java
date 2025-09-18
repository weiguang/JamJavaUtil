package com.okayjam.code.leetcode;

/**
 * 208. 实现 Trie (前缀树)
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补全和拼写检查。
 * https://leetcode.cn/problems/implement-trie-prefix-tree/description/
 *
 * @author JamChen jamchen@tencent.com
 * @date 2025/09/18 10:01
 **/
public class Trie {
    private Trie[] children;
    private boolean isEnd;
    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }
    public void insert(String word) {
        if(word == null || word.isEmpty()) return;
        Trie current = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(current.children[c - 'a'] == null) current.children[c - 'a'] = new Trie();
            current =  current.children[c - 'a'];
        }
        current.isEnd = true;
    }

    public boolean search(String word) {
        Trie trie = searchPre(word);
        return trie!=null && trie.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPre(prefix) != null;
    }

    public Trie searchPre(String pre) {
        if(pre == null || pre.isEmpty()) return null;
        Trie node = this;
        for (int i = 0; i < pre.length(); i++) {
            char c = pre.charAt(i);
            if (node.children[c - 'a'] == null) return null;
            node = node.children[c - 'a'];
        }
        return node ;
    }
}
