package com.zhumin.ioc.bean;

import lombok.Data;
import lombok.ToString;

/**
* 构造函数的传参属性类
* @author 朱敏
* @date 2018/1/15 0015 10:49
*/
@Data
@ToString
public class ConstructorArg {

    private int index;
    private String ref;
    private String name;
}
