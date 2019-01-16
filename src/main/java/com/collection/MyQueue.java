package com.collection;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/7/20 10:43.
 *
 * Implement Queue with LinkedList
 */
public class MyQueue<T> {

    private Queue<T> storage = new LinkedList<T>();
    /** 将指定的元素插入队尾，失败会返回false，add()会在插入失败时抛出异常*/
    public boolean offer(T v) {
        if (storage.offer(v)) return true;
        else return false;
    }
    /** 检索，但是不移除队列的头，如果此队列为空，则返回 null */
    public T peek() {
        return storage.peek();

    }
    /** 检索，但是不移除此队列的头 */
    /** 此方法与 peek 方法的惟一不同是，如果此队列为空，它会抛出一个异常 */
    public T element() {
        return storage.element();
    }
    /** 检索并移除此队列的头，如果队列为空，则返回 null */
    public T poll() {
        return storage.poll();
    }
    /** 检索并移除此队列的头 */
    /** 此方法与 poll 方法的不同在于，如果此队列为空，它会抛出一个异常 */
    public T remove() {
        return storage.remove();
    }
    /** 队列是否为空 */
    public boolean empty() {
        return storage.isEmpty();
    }
    /** 打印队列元素 */
    public String toString() {
        return storage.toString();
    }

}
