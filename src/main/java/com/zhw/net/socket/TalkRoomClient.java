package com.zhw.net.socket;

import java.io.*;
import java.net.Socket;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/22 0022
 * @desc 聊天室客户端
 **/
public class TalkRoomClient {
    public static void main(String[] args) {
        try {
            final  Socket socket = new Socket("localhost",8888);
            getInitMsg(socket);
            while (true){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //发送消息
                        try {
                            sendMsg(socket ,"asdasdad");
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }){}.start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //接收消息
                        ReceiveMsg(socket);
                    }
                }){}.start();
            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void getInitMsg(Socket socket){
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String echo = bufferedReader.readLine();
            System.out.println(echo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static void sendMsg(Socket socket,String msg) throws  Exception{
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(msg);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    //接收消息
    private static void ReceiveMsg(Socket socket){
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String echo = bufferedReader.readLine();
            System.out.println(echo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
