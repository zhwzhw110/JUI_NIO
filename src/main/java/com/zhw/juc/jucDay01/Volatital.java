package com.zhw.juc.jucDay01;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/2 0002
 * @desc volatital关键字测试
 **/
public class Volatital {

    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();
        new Thread(demo).start();
        while (true){
            //刷新内存
            synchronized (demo){
                if(demo.isFalg()){
                    System.out.println("--------------------");
                    break;
                }
            }

        }
    }
}

class ThreadDemo implements Runnable{
    private boolean falg = false;
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
        falg = true;
        System.out.println("falg=="+falg);
    }

    public boolean isFalg() {
        return falg;
    }

    public void setFalg(boolean falg) {
        this.falg = falg;
    }
}
