package com.okayjam.code.leetcode;

/**
 * 641. 设计循环双端队列
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/07/03 10:18
 **/
public class MyCircularDeque {

    private final int capture;
    private int front;
    private int size;
    private final int[] bucket;

    public MyCircularDeque(int k) {
        capture = k;
        front = 0;
        size = 0;
        bucket = new int[k];
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        front = (front - 1 + capture) % capture;
        bucket[front] = value;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        bucket[(front + size) % capture] = value;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % capture;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        size--;
        return true;
    }

    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return bucket[front];
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return bucket[(front + size - 1) % capture];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capture;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
