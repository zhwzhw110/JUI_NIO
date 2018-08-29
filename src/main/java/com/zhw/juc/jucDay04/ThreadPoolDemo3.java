package com.zhw.juc.jucDay04;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/16 0016
 * @desc ScheduledExecutorService 延迟或者定时执行
 **/
public class ThreadPoolDemo3 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(5);

        SubThreadCallable subThreadCallable = new SubThreadCallable();
        long delay = 1; //延迟
        TimeUnit timeUnit = TimeUnit.SECONDS;

        ArrayList<Future> futureList = new ArrayList<Future>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> schedule = scheduledExecutorService.schedule(subThreadCallable, delay, timeUnit);
            futureList.add(schedule);
        }
        //表示延迟1秒钟执行线程

        try {
            for (int i = 0; i < futureList.size(); i++) {
                System.out.println(futureList.get(i).get());
            }
            System.out.println(new Random().nextInt(100));
        }catch (Exception e){
            e.printStackTrace();
        }
        scheduledExecutorService.shutdown();

    }
}

class SubThreadCallable implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        int sum  = 0;
        for (int i = 0; i < 100 ; i++) {
            sum = sum + i;
        }
        return sum;
    }
}