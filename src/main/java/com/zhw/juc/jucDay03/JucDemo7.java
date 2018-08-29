package com.zhw.juc.jucDay03;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/16 0016
 * @desc 读写锁测试ReadWriteLock
 **/
public class JucDemo7 {
    public static void main(String[] args) {

     /*   BigDecimal bigDecimal = new BigDecimal(10.65);
        int v = (int) bigDecimal.floatValue();
        System.out.println(v);
        System.out.println((int)(Math.random()*100));*/

     //创建100个线程写数据，创建100个线程读取数据
        final ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
        for (int i = 0;i<100;i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readWriteLockDemo.set(finalI);
                }
            }, "写锁A").start();
        }
        for (int i = 0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readWriteLockDemo.get();
                }
            } ,"读锁"+i).start();
        }

    }
}
class ReadWriteLockDemo{
    private int number = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    public void get(){
        try{
            lock.readLock().lock(); //添加读锁
            System.out.println(Thread.currentThread().getName()+":"+number);
        }finally {
            lock.readLock().unlock(); //解除读锁
        }
    }

    public void set(int number){
        try{
            lock.writeLock().lock(); //添加写锁
            System.out.println(Thread.currentThread().getName());
            this.number = number;
        }finally {
            lock.writeLock().unlock();//解除写锁
        }
    }

}
