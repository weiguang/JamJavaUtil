package com.okayjam.code.leetcode.solution;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 731. 我的日程安排表 II
 * TreeMap 差分数组法（逻辑清晰，代码极简）
 * 思路
 * 将问题抽象为“公交车上下车”问题：
 * 在 startTime 时刻有 1 个日程开始（预订数 +1）；
 * 在 endTime 时刻有 1 个日程结束（预订数 -1）。
 * 使用 TreeMap 按时间点升序记录变化量。尝试插入新日程后，顺次累加时间点上的变化量 active：
 * 一旦 active >= 3，说明在该时间段有 3 个或以上的日程同时生效，触发回滚（恢复原来的 TreeMap 计数）并返回 false。
 * 若扫描完毕均未超过 2，则保留插入并返回 true。
 *
 * @author weiguang chen2621978@gamil.com
 * @since 2026/11/01 20:20
 **/
public class MyCalendarTwo {

    private final TreeMap<Integer, Integer> map;

    public MyCalendarTwo() {
        map = new TreeMap<>();
    }

    public boolean book(int startTime, int endTime) {
        map.put(startTime, map.getOrDefault(startTime, 0) + 1);
        map.put(endTime, map.getOrDefault(endTime, 0) - 1);

        int active = 0;
        for (int count : map.values()) {
            active += count;
            if (active >= 3) {
                // 恢复原状（回滚）
                map.put(startTime, map.get(startTime) - 1);
                map.put(endTime, map.get(endTime) + 1);
                return false;
            }
        }
        return true;
    }
}
