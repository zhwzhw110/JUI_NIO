package com.zhw.juc.jucDay03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/15 0015
 * @desc 循环打印ABC, 依次打印10遍
 **/
public class JucDemo6 {
    public static void main(String[] args) {
       final loopTreadDemo loopTreadDemo = new loopTreadDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 2; i++) {
                    loopTreadDemo.loopA(i);
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 2; i++) {
                    loopTreadDemo.loopB(i);
                }
            }
        },"B").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 2; i++) {
                    loopTreadDemo.loopC(i);
                }
            }
        },"C").start();
    }
}

class loopTreadDemo{
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();


    public void loopA(int loopCount){
        lock.lock();
        try {
            if(flag!=1){
                //不是1的都沉睡
                condition1.await();
            }
            for (int i = 0; i < 5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"A"+i+":"+loopCount);
            }
            flag=2;
            //唤醒2
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void loopB(int loopCount){
        lock.lock();
        try {
            if(flag!=2){
                //不是1的都沉睡
                condition2.await();
            }
            for (int i = 0; i < 10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"B"+i+":"+loopCount);
            }
            flag=3;
            //唤醒2
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void loopC(int loopCount) {
        lock.lock();
        try {
            if (flag != 3) {
                //不是1的都沉睡
                condition3.await();
            }
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + "C" + i + ":" + loopCount);
            }
            flag=1;
            //唤醒2
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
