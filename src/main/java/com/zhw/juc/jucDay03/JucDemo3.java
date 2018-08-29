package com.zhw.juc.jucDay03;

import java.util.concurrent.CountDownLatch;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/8 0008
 * @desc 利用它可以实现类似计数器的功能。比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
 **/
public class JucDemo3 {

    public static void main(String[] args) {
        //初始化计时器
        CountDownLatch countDownLatch = new CountDownLatch(10);
        TestCountDownLatch testCountDownLatch = new TestCountDownLatch(countDownLatch);
        long starTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(testCountDownLatch).start();
        }
        try {
            //线程等待，等待计时器中扥数据变为0以后再执行线程中的代码
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("时长为："+(endTime-starTime));

    }
}
class TestCountDownLatch implements Runnable{
    CountDownLatch countDownLatch;
    public TestCountDownLatch(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        try {
            synchronized (this){ //testCountDownLatch对象
                for (int i = 0; i < 50000; i++) {
                    if(i%2==0){
                        System.out.println(i);
                    }
                }
            }
        }finally {
            //每次执行都要减去1
            countDownLatch.countDown();
        }


    }
}
