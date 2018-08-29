package com.zhw.juc.jucDay01;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/3 0003
 * @desc 线程测试2
 **/
public class ThreadDemo2 {
    public static void main(String[] args) {
       /* SubThread1 subThread1 = new SubThread1();
        subThread1.start();
        SubThread2 subThread2 = new SubThread2();
        subThread2.start();*/

       new Thread(){
           @Override
           public void run() {
               for (int i = 0; i < 100; i++) {
                   if(i%2 == 0 ){
                       System.out.println(Thread.currentThread().getName()+"偶:"+i);
                   }
               }
           }
       }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if(i%2 != 0 ){
                        System.out.println(Thread.currentThread().getName()+"奇:"+i);
                    }
                }
            }
        }.start();

    }
}

class SubThread1 extends  Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i%2 == 0 ){
                System.out.println(Thread.currentThread().getName()+"偶:"+i);
            }
        }
    }
}

class SubThread2 extends  Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i%2 != 0 ){
                System.out.println(Thread.currentThread().getName()+"奇:"+i);
            }
        }
    }
}