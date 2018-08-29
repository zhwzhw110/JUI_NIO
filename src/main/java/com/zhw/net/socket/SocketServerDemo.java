package com.zhw.net.socket;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/22 0022
 * @desc TCP服务器连接地址
 * 因为Socket是TCP协议，而http底层协议就是TCP协议，所以可以在浏览器上直接访问 输入地址 http://localhost:8888
 **/
public class SocketServerDemo {
    public static void main(String[] args) throws  Exception{
        //创建服务器指定端口
        ServerSocket serverSocket = new ServerSocket(8888);
        //接收客户端连接 阻塞式连接
        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("一个客户端建立连接");

            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
            bw.write("hello world");
            bw.newLine(); //因为在客户端使用了readline,所以要加上换行符
            bw.flush();
        }



    }
}
