package com.zhumin.ioc.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhumin.ioc.bean.BeanDefinition;
import com.zhumin.ioc.utils.JsonUtils;

import java.io.InputStream;
import java.util.List;

/**
* 创建容器
* @author 朱敏
* @date 2018/1/15 0015 17:00
*/
public class JsonApplicationContext extends BeanFactoryImpl {

    private String fileName;

    public JsonApplicationContext(String fileName) {
        this.fileName = fileName;
    }

    public void init(){
        loadFile();
    }

    private void loadFile(){

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        List<BeanDefinition> beanDefinitions = JsonUtils.readValue(is,new TypeReference<List<BeanDefinition>>(){});

        if(beanDefinitions != null && !beanDefinitions.isEmpty()) {

            for (BeanDefinition beanDefinition : beanDefinitions) {
                registerBean(beanDefinition.getName(), beanDefinition);
            }
        }

    }


}
