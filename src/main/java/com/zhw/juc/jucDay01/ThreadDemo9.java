package com.zhw.juc.jucDay01;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/6 0006
 * @desc 线程通信之测试
 **/
public class ThreadDemo9 {
    public static void main(String[] args) {
        PrintNum printNum = new PrintNum();
        Thread t1 = new Thread(printNum);
        Thread t2 = new Thread(printNum);

        t1.setName("甲");
        t2.setName("乙");

        t1.start();
        t2.start();
    }
}

class PrintNum implements Runnable{
    private int num = 1;
    @Override
    public void run() {
        while(true){
            synchronized (this){
                //唤醒等待的线程，即wait之后的线程
                notify();
                if(num<=100){
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":"+num);
                    num++;
                }else {
                    break;
                }
                //当前线程进入等待状态
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}