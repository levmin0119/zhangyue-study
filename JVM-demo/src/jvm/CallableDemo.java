package jvm;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableDemo {

    public static void main(String[] args) throws Exception {
        Callable<String> callable = new MyCallable();
        FutureTask<String> callableFutureTask = new FutureTask<String>(callable);
        Thread callableDemo = new Thread(callableFutureTask, "callable线程");
        callableDemo.start();

        System.out.println(callableDemo.getName()+"--"+callableFutureTask.get());

    }

    public static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(i);
            }
            return "线程执行完毕";
        }
    }
}
