package com.zhw.juc.jucDay01;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/6 0006
 * @desc 店员，生产者和消费者
 **/
public class ThreadDemo10 {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Thread productor = new Thread(clerk);
        Thread customer = new Thread(clerk);

        productor.setName("productor");
        customer.setName("customer");

        productor.start();
        customer.start();

    }
}
class Clerk implements Runnable{
    private int goods = 0;
    @Override
    public void run(){
        while(true){
            synchronized (this){
                String name = Thread.currentThread().getName();
                notify();
                if(goods>=20){
                    if("productor".equals(name)){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if(goods==0){
                    if("customer".equals(name)){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }

                if("productor".equals(name)){
                    if(goods<20) {
                        goods++;
                    }
                }
                if("customer".equals(name)){
                    if(goods>0) {
                        goods--;
                    }
                }
                try {
                    Thread.currentThread().sleep(50);
                    System.out.println(name+":"+goods);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
