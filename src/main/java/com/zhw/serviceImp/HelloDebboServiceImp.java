package com.zhw.serviceImp;

import com.zhw.service.HelloDebboService;

/**
 * @author: zhangHaiWen
 * @date : 2018/4/2 0002 上午 8:06
 * @DESC :
 */
public class HelloDebboServiceImp implements HelloDebboService{

    @Override
    public void SayHello(String name) {
        System.out.println("hello"+name);
    }

}
