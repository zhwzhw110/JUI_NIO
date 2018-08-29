package com.zhw.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author <a href='mailto:zhw@xxlc360.com'>ZhangHaiWen</a>
 * @create 2018/8/22 0022
 * @desc URLdemo测试
 **/
public class URLDemo {
    public static void main(String[] args) throws  Exception {
        URL url = new URL("http://www.baidu.com");

        System.out.println(url.getHost());
        System.out.println(url.getPort());
        System.out.println(url.getPath());
        System.out.println(url.getRef());
        System.out.println(url.getQuery());

        InputStream inputStream = url.openStream();
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br  = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        String str = sb.toString();
        System.out.println(str);

    }
}
