package com.okayjam.test;


import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/07/10 16:20
 **/
public class TestJam {
    static class RankObject implements Comparable<RankObject> {
        int score;
        String name;
        public RankObject(String name,int score) {
            this.score = score;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            RankObject that = (RankObject) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "RankObject{" +
                    "score=" + score +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public int compareTo(@NotNull RankObject o) {
            return this.score - o.score;
        }
    }
    public static void main(String[] args) {
//        System.out.println(new TestJam().convertToBase7(-101));
        SortedSet<RankObject> set = new TreeSet<>(Comparator.reverseOrder());
        set.add(new RankObject("a",1));
        set.add(new RankObject("b",5));
        set.add(new RankObject("b",4));
        set.add(new RankObject("c",-1));
        set.add(new RankObject("a",6));
        for (RankObject entry : set) {
            System.out.println(entry);
        }


    }

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int area =  dfs(grid, i, j);
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }
    public int dfs(int[][] grid, int i, int j) {
        if  (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return 0;
        }
        if (grid[i][j] == 0 || grid[i][j] == 2) {
            return 0;
        }
        // 标识已经遍历过的内容
        grid[i][j] = 2;
        int res = dfs(grid, i + 1, j );
        res += dfs(grid, i - 1, j );
        res += dfs(grid, i , j + 1 );
        res += dfs(grid, i,  j - 1);
        return res + 1;
    }

    public String convertToBase7(int num) {
        return Integer.toString(num, 7);
//        int n = Math.abs(num);
//        StringBuilder sb = new StringBuilder();
//        while (n > 0) {
//            sb.append(n % 7);
//            n = n / 7;
//        }
//        return (num > 0 ? "" : "-") + sb;
    }

    public int lengthOfLongestSubstring(String s) {
        if  (s == null || s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                start = Math.max(start, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - start + 1);
        }
        return max;
    }


}
