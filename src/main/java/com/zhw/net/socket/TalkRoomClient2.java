package com.zhw.net.socket;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/23 0023
 * @desc 聊天室2客户端
 **/
public class TalkRoomClient2 {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost",8888);

        //手动创建线程池
        int corePoolSize = 3;
        int maximumPoolSize = 5;
        long keepAliveTime = 5;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue);

       // for (int i = 0; i < 10; i++) {
            SendMsg sendMsg = new SendMsg(socket);
            ReceiveMsg receiveMsg = new ReceiveMsg(socket);

            threadPoolExecutor.submit(sendMsg);
            threadPoolExecutor.submit(receiveMsg);
       // }


    }
}

/**
 * 发送消息
 */
class SendMsg implements  Runnable{
    private BufferedWriter bufferedWriter;
    private BufferedReader console;
    private boolean isRunner = true;

    public SendMsg() {
        console = new BufferedReader(new InputStreamReader(System.in));;
    }

    public SendMsg(Socket socket) {
        this();
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (Exception e){
            isRunner = false;
            CloseUtils.closeAll(console,bufferedWriter);
        }
    }

    @Override
    public void run() {
       while (isRunner){
            try {
                send();
            } catch (IOException e) {
                CloseUtils.closeAll(bufferedWriter);
            }
        }
    }
    //发送数据
    private void send() throws IOException {

        String msg = readLine();
        if(msg!=null && !"".equals(msg)){
            System.out.println(Thread.currentThread().getName()+":发送了数据:"+msg);
            bufferedWriter.write(msg);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }

    //从控制台获取数据
    private String readLine(){
        try {
            return console.readLine();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return "";
    }

}

/**
 * 接收消息
 */
class ReceiveMsg implements  Runnable{
    private BufferedReader bufferedReader;
    private boolean isRunner = true;
    public ReceiveMsg(Socket socket) {
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch (Exception e){
            isRunner =false;
            CloseUtils.closeAll(bufferedReader);
        }
    }

    @Override
    public void run() {
        while (isRunner){
            try {
                getMsg();
            } catch (IOException e) {
                CloseUtils.closeAll(bufferedReader);
            }
        }
    }

    //从服务端接收数据
    private void getMsg() throws IOException{
        String msg = bufferedReader.readLine();
        System.out.println(Thread.currentThread().getName()+"接收到消息==>"+msg);
    }
}

