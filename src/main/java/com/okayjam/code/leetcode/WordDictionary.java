package com.okayjam.code.leetcode;

/**
 * https://leetcode.cn/problems/design-add-and-search-words-data-structure/description/
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2025/09/19 20:30
 **/

class WordDictionary {

    private WordDictionary[] children;
    private boolean isEnd;

    public WordDictionary() {
        children = new WordDictionary[26];
        isEnd = false;
    }

    public void addWord(String word) {
        WordDictionary node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new WordDictionary();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        return search(word, 0, this);
    }

    public boolean search(String word, int index, WordDictionary node) {
        if (index == word.length()) {
            return node.isEnd;
        }
        char ch = word.charAt(index);
        if (ch == '.') {
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null && search(word, index + 1, node.children[i])) {
                    return true;
                }
            }
        } else {
            WordDictionary child = node.children[ch - 'a'];
            if (child != null && search(word, index + 1, child)) {
                return true;
            }
        }
        return false;
    }
}