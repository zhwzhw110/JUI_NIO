package com.zhw.juc.jucDay03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/9 0009
 * @desc lock和unlock的替换同步锁实现消费者和生产者之间的关系
 **/
public class JucDemo5 {

    public static void main(String[] args) {
        Salesclerk1 salesclerk = new Salesclerk1();
        Producer1 producer = new Producer1(salesclerk);
        Customer1 customer = new Customer1(salesclerk);

        new Thread(producer,"生产者 A").start();
        new Thread(producer,"生产者 B").start();

        new Thread(customer,"消费者 C").start();


    }
}
//店员
class  Salesclerk1{
    private int goods = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public  void addGoods(){
            lock.lock();
            try{
                while(true){
                    if(goods>=10){
                        System.out.println("停止生产");
                        try {
                            System.out.println(Thread.currentThread().getName()+":睡着了");
                            //wait();
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println(Thread.currentThread().getName()+":"+ ++goods);
                        //notifyAll();
                        condition.signalAll();
                    }
                }
            }finally {
                lock.unlock();
            }


    }

    public  void saleGoods(){
        lock.lock();
        try {
            while(true){
                if(goods<=0){
                    System.out.println("停止消费");
                    try {
                        System.out.println(Thread.currentThread().getName()+":睡着了");
                        //wait();
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println(Thread.currentThread().getName()+":"+ goods--);
                    //notifyAll();
                    condition.signalAll();
                }
            }
        }finally {
            lock.unlock();
        }
    }
}
//生产者
class Producer1 implements Runnable{
    private Salesclerk1 salesclerk;

    public Producer1(Salesclerk1 salesclerk){
        this.salesclerk = salesclerk;
    }

    @Override
    public void run(){
        salesclerk.addGoods();
    }

}

//消费者
class Customer1 implements Runnable{
    private Salesclerk1 salesclerk;

    public Customer1(Salesclerk1 salesclerk){
        this.salesclerk = salesclerk;
    }

    @Override
    public void run(){
        salesclerk.saleGoods();
    }
}
