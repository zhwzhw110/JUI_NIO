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
 * @create 2018/8/23 0023
 * @desc 聊天室2服务端
 **/
public class TalkRoom1Server {

    public static void main(String[] args) throws Exception {
        //手动创建线程
        int corePoolSize = 10;
        int maximumPoolSize = 10;
        long keepAliveTime = 5;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue);

        //获取数据
        ServerSocket serverSocket = new ServerSocket(8888);
        DispatcherMsg dispatcherMsg = new DispatcherMsg(serverSocket);
        threadPoolExecutor.submit(dispatcherMsg);
    }
}

class DispatcherMsg implements Runnable{
   private ServerSocket serverSocket;
   private boolean isRunner = true;

    public DispatcherMsg(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        while (isRunner){
            try {
                Socket socket = serverSocket.accept();
                System.out.println("一个客户端连接==");
                dispatcherMsg(socket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

  private void dispatcherMsg(Socket socket) {
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter( socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = bufferedReader.readLine();
            if( null!=msg  &&  !"".equals(msg) ){
                bufferedWriter.write(msg);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }else{
                bufferedWriter.write("没有消息");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            System.out.println("转发消息==》"+msg);
        }catch (IOException e){
            isRunner = false;
            CloseUtils.closeAll(bufferedWriter,bufferedReader);
        }
    }

}
