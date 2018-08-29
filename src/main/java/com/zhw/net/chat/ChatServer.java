package com.zhw.net.chat;

import com.zhw.net.socket.CloseUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/23 0023
 * @desc 聊天服务器
 **/
public class ChatServer {
    private List<Channal> channals= new ArrayList<Channal>();

    public static void main(String[] args) {
        new ChatServer().start();
    }

    public void start(){
        try {
            ServerSocket serverSocket = new ServerSocket(8888);

            //手动创建线程池
            int corePoolSize = 3;
            int maximumPoolSize = 5;
            long keepAliveTime = 5;
            TimeUnit unit = TimeUnit.SECONDS;
            BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue);

            while(true){
                Socket socket = serverSocket.accept();
                Channal channal = new Channal(socket);
                channals.add(channal);
                System.out.println(Thread.currentThread().getName()+" 创建了一个连接,数量为："+channals.size());
                threadPoolExecutor.submit(channal);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 一个管道包含输入流和输出流
     */
    private class Channal implements Runnable{
        private BufferedReader bufferedReader;
        private BufferedWriter bufferedWriter;
        private boolean isRunner = true;

        public Channal(Socket socket){
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            }catch (Exception e){
                isRunner = false;
                CloseUtils.closeAll(bufferedReader,bufferedWriter);
                channals.remove(this);
            }
        }
        //发送消息
        private void send(String msg){
            if(null!=msg && !"".equals(msg)){
                try {
                    bufferedWriter.write(msg);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } catch (IOException e) {
                    isRunner = false;
                    CloseUtils.closeAll(bufferedWriter);
                    channals.remove(this);
                }
            }

        }

        //接收消息
        private String receive(){
            String msg = "";
            try {
                msg = bufferedReader.readLine();
            } catch (IOException e) {
                isRunner = false;
                CloseUtils.closeAll(bufferedReader);
            }
            return msg;
        }

        //发送给其他的客户端
        private void SendOthers(){
            String myMsg = receive();
            System.out.println("客户端发送来的信息："+myMsg);
            for (Channal channal : channals) {
                if(channal != this){ //不发送给自身
                    channal.send(myMsg);
                }
            }
        }

        @Override
        public void run() {
            while (isRunner){
                SendOthers();
            }
        }
    }

}
