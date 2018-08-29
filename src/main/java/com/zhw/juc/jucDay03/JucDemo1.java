package com.zhw.juc.jucDay03;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/7 0007
 * @desc volatile关键字
 **/
public class JucDemo1 {
    public static void main(String[] args) {
        VolatileThread2 thread = new VolatileThread2();
        new Thread(thread){}.start();
        new Thread(thread){}.start();

 /*       Thread t1 = new Thread(thread);
        t1.start();
        //运行非常快，主线程没有办法在次从主存中获取数据
        while (true){
            //synchronized (thread){
                if(thread.isFlag()){
                    System.out.println("########");
                    break;
                }
            //}

        }*/

    }
}

class VolatileThread implements Runnable{
    private volatile boolean flag = false;
    @Override
    public void run(){

        flag =true;
        System.out.println("flag="+isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
class VolatileThread1 implements Runnable{
    private volatile int age=10 ;
    @Override
    public void run(){
        age++;
        System.out.println(Thread.currentThread().getName()+"age:"+age);
    }

}

class VolatileThread2 implements Runnable{
    private AtomicInteger age = new AtomicInteger();
    @Override
    public void run(){
        age.getAndIncrement();
        System.out.println(Thread.currentThread().getName()+"age:"+age);
    }

}