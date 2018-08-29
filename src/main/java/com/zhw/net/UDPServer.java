package com.zhw.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/22 0022
 * @desc UDP服务器
 **/
public class UDPServer {
    public static void main(String[] args) throws  Exception{
        DatagramSocket server = new DatagramSocket(8888); //绑定端口
        byte [] containe = new byte[1024]; //创建数据容器
        DatagramPacket packet = new DatagramPacket(containe,containe.length); //创建数据包
        server.receive(packet); //接收数据包
        byte[] data = packet.getData(); //获取数据
        System.out.println(new String(data));
        server.close();

    }
}
