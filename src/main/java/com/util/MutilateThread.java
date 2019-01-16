package com.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/10/17 15:46.
 */
public class MutilateThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestFutureTask();
        //ThreadTest.test1();
        //ThreadTest.test2();
//        new ThreadTest().test3();
    }

    /**
     * 测试 FutureTask
     */
    public static void TestFutureTask () {
        MyCallable callable1 = new MyCallable(1000); // 要执行的任务
        MyCallable callable2 = new MyCallable(2000);
        FutureTask<String> futureTask1 = new FutureTask<String>(callable1);// 将Callable写的任务封装到一个由执行者调度的FutureTask对象
        FutureTask<String> futureTask2 = new FutureTask<String>(callable2);
        ExecutorService executor = Executors.newFixedThreadPool(2); // 创建线程池并返回ExecutorService实例
        executor.execute(futureTask1);
        executor.execute(futureTask2);
        while (true) {
            try {
                if (futureTask1.isDone() && futureTask2.isDone()) {// 两个任务都完成
                    System.out.println("Done");
                    executor.shutdown(); // 关闭线程池和服务
                    return;
                }
                if (!futureTask1.isDone()) { // 任务1没有完成，会等待，直到任务完成
                    System.out.println("FutureTask1 output=" + futureTask1.get());
                }
                System.out.println("Waiting for FutureTask2 to complete");
                String s = futureTask2.get(200L, TimeUnit.MILLISECONDS);
                if (s != null) {
                    System.out.println("FutureTask2 output=" + s);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                //do nothing
            }
        }

    }

}


class MyCallable implements Callable<String> {
    private long waitTime;

    public MyCallable(int timeInMillis) {
        this.waitTime = timeInMillis;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(waitTime); //return the thread name executing this callable task
        return Thread.currentThread().getName();
    }
}

/**
 * Created by Weiguang Chen <chen2621978@gmail.com> on 2017/10/17 16:15.
 * 测试 Callable
 */
 class ThreadTest<T> implements Callable {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    @Override
    public Object call() throws Exception {
        for (int i = 1; i <= 100; i++) {
            //Do something what U want
            atomicInteger.set(atomicInteger.get() + 1);
        }
        return atomicInteger.get();
    }

    /**
     * 获取线程的返回值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test1() throws ExecutionException, InterruptedException {
        FutureTask future = new FutureTask(new ThreadTest());
        Thread thread = new Thread(future);
        thread.start();
        if (future.isDone()) {
            future.cancel(true);
        }
        System.out.println(future.get());
    }

    /**
     * 通过ExecutorService执行线程
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future submit = executorService.submit(new ThreadTest());
        System.out.println(submit.get());
    }

    /**
     * 批量提交任务
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public  void test3() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<T>> tasks = new ArrayList<>();
        ThreadTest threadTest = new ThreadTest();
        for (int i = 0; i < 5; i++) {
            tasks.add(threadTest);
        }
        //该方法返回某个完成的任务
        Object o = executorService.invokeAny(tasks);
        System.out.println(o);
        System.out.println("One completed!");
        long start = System.currentTimeMillis();
        threadTest = new ThreadTest();
        tasks.clear();
        for (int i = 0; i < 5; i++) {
            tasks.add(threadTest);
        }
        List<Future<T>> futures = executorService.invokeAll(tasks);
        long end = System.currentTimeMillis();
        System.out.println(end - start + " ms之后，返回运行结果！");
        for (int i = 0; i < 5; i++) {
            System.out.println(futures.get(i).get());
        }
    }
}
