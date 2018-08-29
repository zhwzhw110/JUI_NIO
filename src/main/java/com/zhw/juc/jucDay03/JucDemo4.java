package com.zhw.juc.jucDay03;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/9 0009
 * @desc 模拟虚假唤醒，生产者，消费者
 **/
public class JucDemo4 {
    public static void main(String[] args) {
        Salesclerk salesclerk = new Salesclerk();
        Producer producer = new Producer(salesclerk);
        Customer customer = new Customer(salesclerk);

        new Thread(producer,"生产者 A").start();
        new Thread(producer,"生产者 B").start();

        new Thread(customer,"消费者 C").start();


    }
}
//店员
class  Salesclerk{
    private int goods = 0;

    public synchronized void addGoods(){

           while(true){
                if(goods>=10){
                    System.out.println("停止生产");
                    try {
                        System.out.println(Thread.currentThread().getName()+":睡着了");

                        wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println(Thread.currentThread().getName()+":"+ ++goods);
                    notifyAll();
                }
            }

    }

    public synchronized void saleGoods(){

        while(true){
            if(goods<=0){
                System.out.println("停止消费");
                try {
                    System.out.println(Thread.currentThread().getName()+":睡着了");

                    wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println(Thread.currentThread().getName()+":"+ goods--);
                notifyAll();

            }
        }

    }

}
//生产者
class Producer implements Runnable{
    private Salesclerk salesclerk;

    public Producer(Salesclerk salesclerk){
        this.salesclerk = salesclerk;
    }

    @Override
    public void run(){
        salesclerk.addGoods();
    }

}

//消费者
class Customer implements Runnable{
    private Salesclerk salesclerk;

    public Customer(Salesclerk salesclerk){
        this.salesclerk = salesclerk;
    }

    @Override
    public void run(){
        salesclerk.saleGoods();
    }
}