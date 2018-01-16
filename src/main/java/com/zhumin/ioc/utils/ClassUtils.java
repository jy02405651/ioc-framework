package com.zhumin.ioc.utils;

/**
* 加载类工具
* @author 朱敏
* @date 2018/1/15 0015 10:54
*/
public class ClassUtils {

    public static ClassLoader getDefultClassLoader(){
        return  Thread.currentThread().getContextClassLoader();
    }

    public static Class loadClass(String className){
        try {
            return getDefultClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
