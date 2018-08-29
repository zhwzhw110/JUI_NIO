package com.zhw.net.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/22 0022
 * @desc 聊天室服务器
 **/
public class TalkRoomServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);


        int corePoolSize = 2;
        int maximumPoolSize = 10;
        long keepAliveTime = 300;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
        //手动开启一个线程
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue);

        while(true){
            Socket socket = serverSocket.accept();
            DelMsg msg = new DelMsg(socket);
            threadPoolExecutor.submit(msg);
        }

        //待线程池以及缓存队列中所有的线程任务完成后关闭线程池。
        //threadPoolExecutor.shutdown();

    }
}
class DelMsg implements Runnable{
    private Socket socket;
    public DelMsg(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        System.out.println("建立一个连接"+socket+Thread.currentThread().getName());
        sendInitMsg(socket); //初始化消息
        dispatcher(socket); //转发消息
    }

    //发送消息
    private void sendInitMsg(Socket socket){
        try {
            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write("欢迎来到聊天室！");
            bufferedWriter.newLine();
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //并且转发
    private void dispatcher(Socket socket){
        try {
            //接收消息
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String echo = bufferedReader.readLine();
            //并且转发
            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(echo);
            System.out.println("转发消息："+echo);
            bufferedWriter.newLine();
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}





