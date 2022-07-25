package com.okayjam.baseTest;

import org.junit.Test;

import java.time.LocalTime;
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
    public void testSchedulePool() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        Runnable run = () -> {
            try {
                System.out.println(LocalTime.now());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
//        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(run, 0, 6, TimeUnit.SECONDS);
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(run, 0, 2, TimeUnit.SECONDS);
        Object o = scheduledFuture.get();
        System.out.println(o);

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