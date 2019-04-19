package com.okayjam.baseTest;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Chen weiguang <chen2621978@gmail.com>
 * @create: 2019/04/19 10:46
 **/
public class ThreadTest {
    public static void main(String[] args) {

    }

    @Test
    public void testCallable1() throws ExecutionException, InterruptedException {
        ExecutorService pool =  Executors.newSingleThreadExecutor();
        MyThread task = new MyThread();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        pool.submit(futureTask);
        System.out.println(futureTask.get());
    }

    @Test
    public void testCallable2() throws ExecutionException, InterruptedException {
        MyThread task = new MyThread();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }

    @Test
    public void testLock() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        int syncNum = 1;
        try {
            syncNum++;
        }finally {
            lock.unlock();
        }
    }
}



class MyThread implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return 1;
    }
}