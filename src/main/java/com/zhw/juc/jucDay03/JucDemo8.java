package com.zhw.juc.jucDay03;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/16 0016
 * @desc 线程八锁
 *1.非静态方法的锁默认是 this,静态方法的锁为 对应的Class实例 Thread8Demo.Class
 *
 **/
public class JucDemo8 {
    public static void main(String[] args) {
        final Thread8Demo thread8Demo = new Thread8Demo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                thread8Demo.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                thread8Demo.getTwo();
            }
        }).start();

    }
}

class Thread8Demo{
    public static synchronized void getOne(){  //锁对象：Thread8Demo.Class
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }
    public static synchronized void getTwo(){ //锁对象：this
        System.out.println("two");
    }

}
