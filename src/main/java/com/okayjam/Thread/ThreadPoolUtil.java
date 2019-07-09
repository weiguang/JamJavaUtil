package com.okayjam.Thread;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Chen Weiguang <chen2621978@gmail.com>
 * @date 2019/07/08 16:36
 **/
public class ThreadPoolUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolUtil.class);
    public static final int  AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();


    /**
     *  获取线程池方法
     * @param poolName 线程池名称
     * @return 返回线程池，默认线程数 = 核心数 * 2
     */
    public static ThreadPoolExecutor getThreadPool(String poolName) {
        int threadNumber = AVAILABLE_PROCESSORS * 2;
        return getThreadPool(poolName, threadNumber);
    }

    /**
     * 获取线程池方法
     * @return 返回线程池
     */
    public static ThreadPoolExecutor getThreadPool(String poolName, int threadNumber ) {
        // 建立线程名字
        ThreadFactory namedThreadFactory = new MyThreadFactory(poolName);
//                new ThreadFactoryBuilder().setNameFormat(poolName + "-pool-%d").build();

        LOGGER.info("New Thread pool max thread size:{}", threadNumber);
        return new ThreadPoolExecutor(threadNumber, threadNumber,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), namedThreadFactory);
    }

    /**
     * 关闭线程池
     * @param pool 线程池
     */
    public static void poolStopAndWait(ExecutorService pool) {
        // 关闭线程池
        pool.shutdown();
        //判断线程池是否结束
        while (!pool.isTerminated()) {
            try {
                // 等待30分钟
                pool.awaitTermination(30, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                LOGGER.error("Pool wait for stop error,InterruptedException", e);
            } catch (Exception e) {
                LOGGER.error("Thread error：", e);
            }
        }
    }

    /**
     * 定义线程工厂类，实现自定义名称
     * 实现参考： Executors.defaultThreadFactory()
     */
    static class MyThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory(String namePrefix) {
            this.namePrefix = namePrefix+"-pool-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread( r,namePrefix + threadNumber.getAndIncrement());
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
