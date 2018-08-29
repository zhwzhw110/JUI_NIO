package com.zhw.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/22 0022
 * @desc UDP客户端
 **/
public class UDPclient {
    public static void main(String[] args) throws  Exception{
        DatagramSocket client = new DatagramSocket();
        String text = "aaaa";
        byte[] buf = text.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(buf,buf.length, new InetSocketAddress(InetAddress.getLocalHost(),8888)); //发送数据
        client.send(datagramPacket);
        //释放
        client.close();

    }
}
