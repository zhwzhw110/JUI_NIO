package com.zhw.juc.jucDay01;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/2 0002
 * @desc 多线程测试
 **/
public class ThreadDemo1{
    public static void main(String[] args) {
        subThread thread = new subThread();
        //设置线程执行的优先级 并不一定级别越大，一定抢到，只是获取CPU资源的概率变大了
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        for (int i = 0; i <= 100; i++) {
           /* if(i % 10 ==0){
                //释放当前CPU资源的执行权
                Thread.currentThread().yield();
            }*/
          /* if(i==1){
               try {
                   //线程强制睡眠10秒 10000毫秒
                   Thread.currentThread().sleep(10000);
                   //强制加入线程，让该线程先执行
                   thread.join();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }*/
           //判断线程是否存活
           // System.out.println(thread.isAlive());
            System.out.println("Main=="+i);
        }
    }
}
class subThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {

            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}
