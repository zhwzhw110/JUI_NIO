package com.zhw.juc.jucDay02;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/3 0003
 * @desc 懒汉式，饿汉式单例设计模式
 **/
public class SingaltonDemo {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                Singalton singalton1 = Singalton.getSingalton();
                System.out.println(singalton1);
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                Singalton singalton2 = Singalton.getSingalton();
                System.out.println(singalton2);
            }
        }.start();
    }
}

/**
 * 懒汉式单例设计模式
 */
class Singalton {
    private static Singalton singalton = null;

    private Singalton(){

    }
    public static Singalton getSingalton(){
        if(singalton==null){
            synchronized (Singalton.class){
                if (singalton == null) {
                    singalton = new Singalton();
                }
            }
        }
        return singalton;
    }
}

/**
 * 饿汉式单例设计模式
 */
class SingaltonHungry{
    private static final SingaltonHungry singaltonHungry= new SingaltonHungry();
    private SingaltonHungry(){

    }
    public static SingaltonHungry getSingaltonHungry(){
        return  singaltonHungry;
    }
}