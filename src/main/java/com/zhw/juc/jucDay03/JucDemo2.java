package com.zhw.juc.jucDay03;

import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/8 0008
 * @desc TestCopyAndWrite测试
 **/
public class JucDemo2 {
    public static void main(String[] args) {
        TestCopyAndWrite testCopyAndWrite = new TestCopyAndWrite();
        for (int i = 0; i < 10; i++) {
            new Thread(testCopyAndWrite).start();

        }
    }
}

class TestCopyAndWrite implements  Runnable{

   // private static List<String > list = Collections.synchronizedList(new ArrayList<String>());
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
    static {
        list.add("AAA");
        list.add("CCC");
        list.add("BBB");
    }

    @Override
    public void run() {
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()){
            System.out.println(listIterator.next());
            list.add("DDDD");
        }
    }
}