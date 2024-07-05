package com.qehing.server.handlerService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolManager {
    private static final List<ExecutorService> threadPools = new ArrayList<ExecutorService>();
    public static ThreadPoolManager INSTANCE = new ThreadPoolManager();

    private ThreadPoolManager() {
        System.out.println("线程池管理器促使话");
    }

    public static ExecutorService registerThreadPool(
            int corePoolSize,//线程池的核心线程数量
            int maximumPoolSize,//线程池的最大线程数
            long keepAliveTime,//当线程数大于核心线程数时，多余的空闲线程存活的最长时间
            TimeUnit unit,//时间单位
            BlockingQueue<Runnable> workQueue,//任务队列，用来储存等待执行任务的队列
//            ThreadFactory threadFactory,//线程工厂，用来创建线程，一般默认即可
            RejectedExecutionHandler handler//拒绝
    ) {
        System.out.println("注册新线程池");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime, unit, workQueue, handler);
        threadPools.add(threadPoolExecutor);
        return threadPoolExecutor;
    }

    public static void shutdownAll() {
        for (ExecutorService threadPool : threadPools) {
            threadPool.shutdown();
            try {
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    threadPool.shutdownNow();
                    if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                        System.err.println("ThreadPool did not terminate");
                    }
                }
            } catch (InterruptedException ie) {
                threadPool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}
