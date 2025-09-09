package com.okayjam.code.leetcode;

import org.jetbrains.annotations.NotNull;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * com.okayjam.code.leetcode
 *
 * @author JamChen jamchen@tencent.com
 * @date 2025/09/09 19:07
 **/
public class MinStack {

    Deque<Integer> stack;
    Deque<Integer> minStack;

    public MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
    }

    public void push(int val) {
        stack.push(val);
        minStack.push(Math.min(val,minStack.isEmpty()? Integer.MAX_VALUE:minStack.peek()));

    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
