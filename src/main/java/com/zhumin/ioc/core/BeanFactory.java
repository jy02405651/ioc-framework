package com.zhumin.ioc.core;

/**
* 定义bean工厂
* @author 朱敏
* @date 2018/1/15 0015 13:50
*/
public interface BeanFactory {

    public Object getBean(String name) throws Exception;
}
