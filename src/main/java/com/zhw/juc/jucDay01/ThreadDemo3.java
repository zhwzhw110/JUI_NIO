package com.zhw.juc.jucDay01;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/3 0003
 * @desc 火车票售卖100张
 **/
public class ThreadDemo3 {


    public static void main(String[] args) {
        SubThread3 subThread3 = new SubThread3();
        SubThread3 subThread4 = new SubThread3();
        SubThread3 subThread5 = new SubThread3();

        subThread3.setName("A");
        subThread3.start();

        subThread4.setName("B");
        subThread4.start();

        subThread5.setName("C");
        subThread5.start();


    }
}


class SubThread3 extends  Thread{
    static int ticket = 100;
    static Object obj = new Object();
    @Override
    public void run() {
        while(true){
            synchronized (obj){
                if(ticket > 0 ){
                    try{
                        Thread.currentThread().sleep(10);
                        System.out.println(Thread.currentThread().getName()+"售票:"+ticket);
                        ticket = ticket - 1;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
            }
    }
}
