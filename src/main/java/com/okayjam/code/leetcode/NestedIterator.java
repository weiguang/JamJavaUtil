package com.okayjam.code.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 *
 * @author JamChen jamchen@tencent.com
 * @date 2026/06/13 20:30
 **/
public class NestedIterator implements Iterator<Integer> {

    List<NestedInteger> nestedList;
    List<Integer> integerList = new ArrayList<>();
    int currentIndex = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        for (NestedInteger nestedInteger : nestedList) {
            addData(nestedInteger, integerList);
        }
    }

    void addData(NestedInteger n, List<Integer> list) {
        if (n.isInteger()) {
            list.add(n.getInteger());
        }
        for (NestedInteger ns : n.getList()) {
            addData(ns, list);
        }
    }


    @Override
    public Integer next() {
        if (hasNext()) {
            return integerList.get(currentIndex++);
        }
        return null;
    }


    @Override
    public boolean hasNext() {
        return currentIndex < integerList.size();
    }


}


interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}
