package com.zhw.juc.jucDay01;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/3 0003
 * @desc 线程同步方法
 **/
public class ThreadDemo5 {

    public static void main(String[] args) {
        SubThread7 subThread7 = new SubThread7();
        new Thread(subThread7).start();
        new Thread(subThread7).start();
        new Thread(subThread7).start();
    }
}
class SubThread7 implements Runnable{
    private int ticket = 100;
    @Override
    public void run() {
        sellTicket();
    }
    public synchronized void sellTicket(){
        while(true){
            if(ticket>0){
                try {
                    Thread.currentThread().sleep(10);
                }catch (Exception e){
                   e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"出售："+ticket);
                ticket--;
            }else{
                break;
            }
        }
    }
}