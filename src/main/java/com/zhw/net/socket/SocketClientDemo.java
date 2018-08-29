package com.zhw.net.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/22 0022
 * @desc TCP连接客户端
 * 创建客户端时候，必须指定服务器IP地址以及端口号
 **/
public class SocketClientDemo {
    public static void main(String[] args) throws  Exception{
        //创建一个连接，并将其连接到指定的主机上的指定端口
        Socket client = new Socket("localhost",8888);
        InputStream inputStream = client.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = br.readLine();
        System.out.println(line);

    }
}
