package com.zhumin.ioc.core;

import com.zhumin.ioc.bean.BeanDefinition;
import com.zhumin.ioc.bean.ConstructorArg;
import com.zhumin.ioc.utils.BeanUtils;
import com.zhumin.ioc.utils.ClassUtils;
import com.zhumin.ioc.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
* bean工厂实例
* @author 朱敏
* @date 2018/1/15 0015 13:52
*/
public class BeanFactoryImpl implements BeanFactory {


    private static final ConcurrentHashMap<String,Object> beanMap = new ConcurrentHashMap<String,Object>();

    private static final ConcurrentHashMap<String,BeanDefinition> beanDefineMap= new ConcurrentHashMap<String,BeanDefinition>();

    private static final Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<String>());

    /**
    * 2、获取对象让其实例化
    * @param
    * @return
    * @author 朱敏
    * @date 2018/1/15 0015 16:29
    */
    public Object getBean(String name) throws Exception {

        //检查对象是否实例化
        Object bean = beanMap.get(name);
        if(bean != null){
            return bean;
        }
        //如果没有实例化、则创建对象
        bean =  createBean(beanDefineMap.get(name));
        if(bean != null) {
            //对象创建成功以后，注入对象需要的参数
            populatebean(bean);

            //对象存入Map中方便下次使用。
            beanMap.put(name,bean);
        }

        return bean;
    }


    /**
    * 1、容器初始化后第一步是先注册bean到map中
    * @param
    * @return
    * @author 朱敏
    * @date 2018/1/15 0015 16:29
    */
    protected void registerBean(String name, BeanDefinition bd){
        beanDefineMap.put(name,bd);
        beanNameSet.add(name);
    }

    /**
    * 3、对象实例化后、注入参数
    * @param
    * @return
    * @author 朱敏
    * @date 2018/1/15 0015 16:51
    */
    private void populatebean(Object bean) throws Exception {
        Field[] fields = bean.getClass().getSuperclass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                String beanName = field.getName();
                beanName = StringUtils.uncapitalize(beanName);
                if (beanNameSet.contains(field.getName())) {
                    Object fieldBean = getBean(beanName);
                    if (fieldBean != null) {
                        ReflectionUtils.injectField(field,bean,fieldBean);
                    }
                }
            }
        }
    }


    /**
    * 实例化对象
    * @param
    * @return
    * @author 朱敏
    * @date 2018/1/15 0015 16:28
    */
    private Object createBean(BeanDefinition beanDefinition) throws Exception {
        String beanName = beanDefinition.getClassName();
        Class clz = ClassUtils.loadClass(beanName);
        if(clz == null) {
            throw new Exception("can not find bean by beanName");
        }
        List<ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
        if(constructorArgs != null && !constructorArgs.isEmpty()){
            List<Object> objects = new ArrayList<Object>();
            for (ConstructorArg constructorArg : constructorArgs) {
                objects.add(getBean(constructorArg.getRef()));
            }
            return BeanUtils.instanceByCglib(clz,clz.getConstructor(),objects.toArray());
        }else{
            return BeanUtils.instanceByCglib(clz,null,null);
        }
    }
}
