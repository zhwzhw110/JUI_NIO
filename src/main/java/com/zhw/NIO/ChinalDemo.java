package com.zhw.NIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/29 0029
 * @desc NIO通道文件拷贝练习
 **/
public class ChinalDemo {
    public static void main(String[] args) {
       // FileChinalDemo demo = new FileChinalDemo();
        //new Thread(demo){}.start();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(new String("aaa").getBytes());
        System.out.println(byteBuffer);
    }
}

class FileChaannelDemo2 implements Runnable{
    @Override
    public void run() {
      /*  try {
            FileChannel inchannel = FileChannel.open(Paths.get("E:/","1.jpg"), StandardOpenOption.READ);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}

class FileChinalDemo implements Runnable{
    @Override
    public void run() {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            //获取文件输入流
            fis = new FileInputStream("E:\\1.jpg");
            //文件输出流
             fos = new FileOutputStream("E:\\2.jpg");

            //获取文件通道
             inChannel = fis.getChannel();
             outChannel = fos.getChannel();

            //获取缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            //数据读入缓冲区
            while (inChannel.read(byteBuffer)!=-1){
                //将缓冲区转化为写模式
                byteBuffer.flip();
                //数据写入缓冲区
                outChannel.write(byteBuffer);
                //清空缓冲区 position=0 limit = 1024
                byteBuffer.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(inChannel!=null){
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(outChannel!=null){
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
