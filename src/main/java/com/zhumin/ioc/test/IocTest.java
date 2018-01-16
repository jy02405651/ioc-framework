package com.zhumin.ioc.test;


import com.zhumin.ioc.core.JsonApplicationContext;
import com.zhumin.ioc.entity.Apple;

/**
* 测试容器
* @author 朱敏
* @date 2018/1/15 0015 17:19
*/
public class IocTest {

    public static void main(String[] args) throws Exception {
        JsonApplicationContext applicationContext = new JsonApplicationContext("application.json");
        applicationContext.init();
        Apple apple = (Apple) applicationContext.getBean("apple");
        apple.eat();
    }


}
