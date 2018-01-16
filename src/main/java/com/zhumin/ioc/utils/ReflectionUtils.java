package com.zhumin.ioc.utils;

import java.lang.reflect.Field;

/**
* 通过反射注入对象工具
* @author 朱敏
* @date 2018/1/15 0015 13:43
*/
public class ReflectionUtils {

    public static void injectField(Field field, Object obj, Object value) throws IllegalAccessException {
        if(field != null) {
            field.setAccessible(true);
            field.set(obj, value);
        }
    }
}
