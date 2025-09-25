package com.okayjam.code.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 232. 用栈实现队列
 * <a href="https://leetcode.cn/problems/implement-queue-using-stacks/description/">用栈实现队列</a>
 * @author JamChen jamchen@tencent.com
 * @date 2025/09/23 10:12
 **/
public class MyQueue {

    private final Deque<Integer> stack1;
    private final Deque<Integer> stack2;
    public MyQueue() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        if(!stack2.isEmpty()) return stack2.pop();
        while(!stack1.isEmpty()) stack2.push(stack1.pop());
        return stack2.pop();
    }

    public int peek() {
        if(!stack2.isEmpty()) return stack2.peek();
        while(!stack1.isEmpty()) stack2.push(stack1.pop());
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
