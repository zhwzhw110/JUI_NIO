package com.zhw.net.chat;

import com.zhw.net.socket.CloseUtils;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/23 0023
 * @desc 聊天客户端
 **/
public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",8888);

            //手动创建线程池
            int corePoolSize = 3;
            int maximumPoolSize = 5;
            long keepAliveTime = 5;
            TimeUnit unit = TimeUnit.SECONDS;
            BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue);

           SendMsg sendMsg = new SendMsg(socket);
            ReceiveMsg receiveMsg = new ReceiveMsg(socket);

            threadPoolExecutor.submit(sendMsg);
            threadPoolExecutor.submit(receiveMsg);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

/**
 * 发送消息
 */
class SendMsg implements Runnable{

    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader console;
    private boolean isRunning = true;

    //构造器
    public SendMsg(Socket socket) {
        this.socket = socket;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            console = new BufferedReader(new InputStreamReader(System.in));
        }catch (Exception e){
            isRunning = false;
            CloseUtils.closeAll(console,bufferedWriter);
        }
    }
    @Override
    public void run() {
        while (isRunning){
            send();
        }
    }

    //发送消息
    private void send(){
        String msg = read();
        try {
            bufferedWriter.write(msg);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println(Thread.currentThread().getName()+" 发送了一个消息:"+msg);
        } catch (IOException e) {
            isRunning = false;
            CloseUtils.closeAll(bufferedWriter);
        }
    }

    //从控制台读取信息
    private String read() {
        String msg = "" ;
        try {
             msg = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }

}

/**
 * 接收消息
 */
class ReceiveMsg implements Runnable{

    private Socket socket;
    private BufferedReader bufferedReader;
    private boolean isRunning = true;

    //构造器
    public ReceiveMsg(Socket socket) {
        this.socket = socket;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch (Exception e){
            isRunning = false;
            CloseUtils.closeAll(bufferedReader);
        }
    }

    @Override
    public void run() {
        while (isRunning){
            receive();
        }
    }
    //接收消息
    private void receive(){
        try {
            String msg = bufferedReader.readLine();
            System.out.println("读取到一条信息==》"+msg);
        } catch (IOException e) {
            isRunning = false;
            CloseUtils.closeAll(bufferedReader);
        }
    }

}