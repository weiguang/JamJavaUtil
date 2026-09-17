package com.okayjam.code.leetcode;

/**
 * com.okayjam.code.leetcode
 *
 * @author JamChen jamchen@tencent.com
 * @date 2026/09/17 10:47
 **/
public class MyCircularQueue {
    /**
     * Your MyCircularQueue object will be instantiated and called as such:
     * MyCircularQueue obj = new MyCircularQueue(k);
     * boolean param_1 = obj.enQueue(value);
     * boolean param_2 = obj.deQueue();
     * int param_3 = obj.Front();
     * int param_4 = obj.Rear();
     * boolean param_5 = obj.isEmpty();
     * boolean param_6 = obj.isFull();
     */

    // front 始终指向队头
    private int front;
    // 将 tail 修改为指向下一个待插入位置（标准 rear 指针用法），这样入队、出队、获取队尾元素时逻辑更统一。
    private int tail;
    private final int capacity;
    private int size;
    private final int[] bucket;

    public MyCircularQueue(int k) {
        this.capacity = k;
        size = 0;
        front = tail = 0;
        bucket = new int[k];
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        bucket[tail] = value;
        tail = (tail + 1) % capacity;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    public int Front() {
        return isEmpty() ? -1 : bucket[front];
    }

    public int Rear() {
        return isEmpty() ? -1 :bucket[(tail - 1 + capacity) % capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}


