package com.okayjam.code.leetcode;

import java.util.Iterator;

/**
 * 284. 窥视迭代器
 * <a href="https://leetcode.cn/problems/peeking-iterator">284. 窥视迭代器</a>
 *
 * @author JamChen jamchen@tencent.com
 * @date 2025/10/03 11:42
 **/
public class PeekingIterator implements Iterator<Integer> {
    private final Iterator<Integer> iterator;
    private Integer next = null;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        next();
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer t = next;
        next = iterator.hasNext()? iterator.next() : null;
        return t;
    }

    @Override
    public boolean hasNext() {
       return next != null;
    }
}
