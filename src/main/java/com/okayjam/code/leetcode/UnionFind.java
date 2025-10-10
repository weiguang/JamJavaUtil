package com.okayjam.code.leetcode;

/**
 * com.okayjam.code.leetcode
 *
 * @author JamChen jamchen@tencent.com
 * @date 2025/10/10 10:39
 **/
public class UnionFind {

    private int[] parent;
    private int count;

    public int getCount() {
        return count;
    }

    public void addCount() {
        count++;
    }

    public UnionFind(int n) {
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        this.count = 0;
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }

        parent[rootX] = rootY;
        count--;
    }
}
