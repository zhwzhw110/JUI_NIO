package com.zhw.juc.jucDay01;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/3 0003
 * @desc 实现Runnble
 **/
public class ThreadDemo4 {
    public static void main(String[] args) {
        SubThread6 subThread6 = new SubThread6();

        new Thread(subThread6).start();
        new Thread(subThread6).start();
        new Thread(subThread6).start();

    }
}
class SubThread6 implements Runnable{
    private int ticket = 100;
    //Object object = new Object();
    @Override
    public void run() {
        //这个this指向这个类在内存中创建地址，对象分配一个引用自身的指针  subThread6
       synchronized (this){
            while (true){
                if(ticket>0){
                    try {
                        Thread.currentThread().sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":"+ticket);
                    ticket--;
                }else {
                    break;
                }
           }
        }
    }
}
