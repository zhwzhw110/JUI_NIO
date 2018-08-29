package com.zhw.juc.jucDay01;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/3 0003
 * @desc 账户测试
 **/
public class ThreadDemo7 {
    public static void main(String[] args) {
        Account account = new Account();
        Customer customer1 = new Customer(account);
        Customer customer2 = new Customer(account);
        new Thread(customer1).start();
        new Thread(customer2).start();

    }
}

class Account {
    private int money;
    //这里的synchronized 指向的this 是指我的account对象，Main线程中创建的方法共用同一个account,所以同一个锁
    public synchronized  void saveMoney(){
        while(true){
            if(money<3000){
                money = money+1000;
                System.out.println(Thread.currentThread().getName()+":"+money);
            }else{
                break;
            }
        }
    }

}


class Customer implements Runnable{
    private  Account account;

    public Customer(Account account){
        this.account = account;
    }
    @Override
    public void run() {
        account.saveMoney();
    }

}
