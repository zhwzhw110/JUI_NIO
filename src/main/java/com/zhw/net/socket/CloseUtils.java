package com.zhw.net.socket;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/23 0023
 * @desc 关闭流的工具类
 **/
public class CloseUtils {

    public static void closeAll(Closeable  ... eable){
        for (Closeable temp : eable){
            if(temp!=null){
                try {
                    temp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
