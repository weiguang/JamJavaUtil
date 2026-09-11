package com.okayjam.code.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 *
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 *
 * @author JamChen jamchen@tencent.com
 * @date 2026/07/01 08:50
 **/
public class RandomizedSet {
     private final List<Integer> nums;
     private final Map<Integer, Integer> map;
     private final Random random;

    public RandomizedSet() {
        nums = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, nums.size());
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        Integer index  = map.remove(val);
        if (index  == null) {
            return false;
        }
        if (index != nums.size()) {
            Integer lastValue = nums.get(nums.size() - 1);
            nums.set(index, lastValue);
            map.put(lastValue, index);
        }
        nums.remove(nums.size() - 1);
        return true;
    }

    public int getRandom() {
        int idx = random.nextInt(nums.size());
        return nums.get(idx);
    }

    public static void main(String[] args) {
        RandomizedSet set = new RandomizedSet();
        set.insert(0);
        set.insert(1);
        set.remove(0);
        set.insert(2);
        set.remove(1);
        int random1 = set.getRandom();
        System.out.println(random1);
    }
}
