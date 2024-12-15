package com.zy.zhangyue001.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTests implements Callable<String> {
    @Override
    public String call() throws Exception {

        return "通过实现callable接口创建线程测试！！";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableTests callableTests = new CallableTests();
        FutureTask<String> stringFutureTask = new FutureTask<>(callableTests);

        new Thread(stringFutureTask).start();
        String s = stringFutureTask.get();
        System.out.println(s);


    }
}
