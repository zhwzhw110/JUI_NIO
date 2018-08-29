package com.zhw.juc.jucDay01;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/3 0003
 * @desc 账户测试
 **/
public class ThreadDemo6 {
    public static void main(String[] args) {
        Zhanghu zhanghu = new Zhanghu();
        new Thread(zhanghu).start();
        new Thread(zhanghu).start();

    }
}
class Zhanghu implements Runnable{
    private int money = 0;

    @Override
    public void run() {
        while (true){
            synchronized (this){
                if(money<3000){
                    money = money + 1;
                    System.out.println("账户余额："+money);
                }else{
                    break;
                }
            }
        }
    }
}
