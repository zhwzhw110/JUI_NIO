package com.zhw.juc.jucDay01;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/6 0006
 * @desc 消费者，生产者，店员
 **/
public class ThreadDemo11 {
    public static void main(String[] args) {
        NewClerk newClerk = new NewClerk();
        NewProduct newProduct = new NewProduct(newClerk);
        NewCustomer newCustomer = new NewCustomer(newClerk);

        Thread t1 = new Thread(newProduct);
        Thread t2 = new Thread(newCustomer);

        t1.setName("生产者");
        t2.setName("消费者");

        t1.start();
        t2.start();

    }
}

class NewClerk{
    int goods = 0;

    public synchronized void addProduct() {
        if(goods>=20){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            goods ++ ;
            System.out.println(Thread.currentThread().getName()+"生产了"+goods+"号产品");
            notify();
        }
    }

    public synchronized void subtractProduct() {
        if(goods<=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println(Thread.currentThread().getName()+"消费了"+goods+"号产品");
            goods -- ;
            notify();
        }
    }
}

class NewProduct implements Runnable{
    private NewClerk newClerk;
    public NewProduct(NewClerk newClerk){
        this.newClerk = newClerk;
    }
    @Override
    public void run(){
        while (true){
            newClerk.addProduct();
        }
    }
}


class NewCustomer implements Runnable{
    private NewClerk newClerk;
    public NewCustomer(NewClerk newClerk){
        this.newClerk = newClerk;
    }
    @Override
    public void run(){
        while (true){
            newClerk.subtractProduct();
        }
    }
}
