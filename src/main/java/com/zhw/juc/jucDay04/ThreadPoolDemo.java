package com.zhw.juc.jucDay04;

import java.util.concurrent.*;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/16 0016
 * @desc 线程池例子
 **/
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        /*
        ExectorDemo exectorDemo = new ExectorDemo();
        for (int i = 0; i < 10; i++) {
            executorService.submit(exectorDemo);
        }*/

        ExecutorDemoCallable executorDemoCallable = new ExecutorDemoCallable();
        try {
            //执行线程 直接返回线程执行结果
            Future<Integer> future = executorService.submit(executorDemoCallable);
            //执行Callable方式，需要FutureTask实现类的支持，用于接收运算结果
            System.out.println(future.get());
            executorService.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class ExectorDemo implements Runnable{
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}

class ExecutorDemoCallable implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum = sum+i;
        }
        return sum;
    }
}
