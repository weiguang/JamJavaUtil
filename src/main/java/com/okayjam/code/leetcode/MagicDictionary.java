package com.okayjam.code.leetcode;

/**
 * com.okayjam.code.leetcode
 *
 * @author JamChen jamchen@tencent.com
 * @date 2026/10/07 19:48
 **/
public class MagicDictionary {
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private final TrieNode root;

    public MagicDictionary() {
        root = new TrieNode();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
            node.isEnd = true;
        }
    }

    public boolean search(String searchWord) {
        return dfs(root, searchWord, 0, false);
    }

    private boolean dfs(TrieNode node, String word, int index, boolean isModified) {
        if (index == word.length()) {
            // 必须遍历完单词，且正好修改过 1 次字符且到达节点结尾
            return isModified && node.isEnd;
        }

        int idx = word.charAt(index) - 'a';
        for (int i = 0; i < 26; i++) {
            if (node.children[i] == null) {
                continue;
            }

            if (i == idx) {
                // 字符相同：不消耗修改机会
                if (dfs(node.children[i], word, index + 1, isModified)) {
                    return true;
                }
            } else if (!isModified) {
                // 字符不同且尚未修改过：消耗一次修改机会
                if (dfs(node.children[i], word, index + 1, true)) {
                    return true;
                }
            }
        }
        return false;
    }
}
