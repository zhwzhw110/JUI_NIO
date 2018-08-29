package com.zhw.juc.jucDay04;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/16 0016
 * @desc ThreadPoolExecutor测试类
 **/
public class ThreadPoolDemo2 {
    public static void main(String[] args) {

        int corePoolSize = 2;
        int maximumPoolSize = 5;
        long keepAliveTime = 3;
        //设置时间单位，秒
        TimeUnit timeUnit = TimeUnit.SECONDS;
        //设置线程池缓存队列的排队策略为FIFO，并且指定缓存队列大小为5
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,timeUnit,workQueue);

        SubThread subThread = new SubThread();
        executor.submit(subThread);
        //待线程池以及缓存队列中所有的线程任务完成后关闭线程池。
        executor.shutdown();

    }
}
class SubThread implements Runnable{
    @Override
    public void run(){
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum+=i;
        }
        System.out.println(sum);
    }
}
