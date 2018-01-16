package com.zhumin.ioc.bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
* 定义一个bean的属性类
* @author 朱敏
* @date 2018/1/15 0015 10:48
*/
@Data
@ToString
public class  BeanDefinition {

    private String name;

    private String className;

    private String interfaceName;

    private List<ConstructorArg> constructorArgs;

    private List<PropertyArg> propertyArgs;
}
