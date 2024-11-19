package com.zy.zhangyue001.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池自定义一个拒绝策略
 */
public class CustomizeDiscardOldestRejectPolicy implements RejectedExecutionHandler {
    private int discardNumber = 5;
    private List<Runnable> tasks = new ArrayList<Runnable>();

    public CustomizeDiscardOldestRejectPolicy(int discardNumber) {
        this.discardNumber = discardNumber;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (executor.getQueue().size() > discardNumber) {
            executor.getQueue().drainTo(tasks, discardNumber);
            tasks.clear();
            if (!executor.isShutdown()) {
                executor.execute(r);
            }

        }

    }
}
