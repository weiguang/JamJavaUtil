package com.okayjam.code.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. 用队列实现栈
 * https://leetcode.cn/problems/implement-stack-using-queues/description/
 *
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2025/09/21 10:33
 **/
public class MyStack {
    private Queue<Integer> q1 ;
    private Queue<Integer> q2 ;
    public MyStack() {
        q1 =  new LinkedList();
        q2 =  new LinkedList();
    }

    public void push(int x) {
        q2.add(x);
        // 每次保持后一个元素在前面，就翻转了顺序
        while (!q1.isEmpty()) {
            q2.add(q1.poll());
        }
        // 保持q2为最新元素
        Queue t= q1;
        q1 = q2;
        q2= t;
    }

    public int pop() {
        return q1.poll();
    }

    public int top() {
        return q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}
