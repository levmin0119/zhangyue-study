package com.zy.zhangyue001.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MyCallable implements Callable<String> {
    private String name;

    public MyCallable(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        return "";
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建一个线程池
//        ExecutorService executorServicePool = Executors.newFixedThreadPool(5);
//        List<Future> futures = new ArrayList<Future>();
//
//        for (int i = 0; i < 5; i++) {
//            MyCallable myCallable = new MyCallable(i + "");
//            Future<String> future = executorServicePool.submit(myCallable);
//            System.out.println("提交一个callable线程：" + i);
//            futures.add(future);
//        }
////        executorServicePool.shutdown();
//
//        for (Future future : futures) {
//            System.out.println("从callable线程获取的结果：" + future.get().toString());
//        }


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds execu.");
            }
        }, 3, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("创建一个延迟1秒执行并且没3秒执行一次的线程。");
            }
        },1, 3, TimeUnit.SECONDS);
    }
}
