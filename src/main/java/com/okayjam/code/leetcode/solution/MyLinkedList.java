package com.okayjam.code.leetcode.solution;

/**
 * 707. 设计链表
 *
 * @author JamChen jamchen@tencent.com
 * @date 2026/10/19 16:03
 **/
public class MyLinkedList {
    private int size;
    private final Node head; // 虚拟头节点
    private final Node tail; // 虚拟尾节点

    private static class Node {
        int val;
        Node next;
        Node prev;

        Node(int val) {
            this.val = val;
        }
    }

    public MyLinkedList() {
        this.size = 0;
        this.head = new Node(0);
        this.tail = new Node(0);
        // 初始化链表，双哨兵互相连接
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    // 私有辅助方法：根据 index 获取对应的真实节点（采用折半查找）
    private Node getNode(int index) {
        Node cur;
        if (index < size / 2) {
            cur = head;
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
        } else {
            cur = tail;
            for (int i = 0; i < size - index; i++) {
                cur = cur.prev;
            }
        }
        return cur;
    }

    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        return getNode(index).val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;

        // 确定插入位置的后继节点与前驱节点
        Node succ = (index == size) ? tail : getNode(index);
        Node pred = succ.prev;

        Node node = new Node(val);
        node.prev = pred;
        node.next = succ;
        pred.next = node;
        succ.prev = node;

        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        Node toDelete = getNode(index);
        Node pred = toDelete.prev;
        Node succ = toDelete.next;

        pred.next = succ;
        succ.prev = pred;

        size--;
    }
}
