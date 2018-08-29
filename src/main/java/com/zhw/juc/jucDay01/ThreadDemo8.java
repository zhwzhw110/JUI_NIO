package com.zhw.juc.jucDay01;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/6 0006
 * @desc 死锁问题演示
 **/
public class ThreadDemo8 {

    static StringBuffer sb1 = new StringBuffer();
    static StringBuffer sb2 = new StringBuffer();

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                synchronized (sb1){
                    sb1.append("A");
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (sb2){
                        sb2.append("A");
                        System.out.println(sb1);
                        System.out.println(sb2);
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                synchronized (sb2){
                    sb1.append("C");
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (sb1){
                        sb2.append("D");
                        System.out.println(sb1);
                        System.out.println(sb2);
                    }
                }
            }
        }.start();
    }

}
