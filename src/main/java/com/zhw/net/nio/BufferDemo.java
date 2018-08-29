package com.zhw.net.nio;

import java.nio.ByteBuffer;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/24 0024
 * @desc NIO缓冲区测试
 **/
public class BufferDemo {
    public static void main(String[] args) {
        //分配一个指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String str ="abcdef";
        byte[] bytes = str.getBytes();
        byteBuffer.put(bytes);
        System.out.println(byteBuffer);
        System.gc();
        byteBuffer.flip();
        System.out.println(byteBuffer);
        byteBuffer.flip();
        System.out.println(byteBuffer);

    }
}
