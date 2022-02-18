package com.stock.springcomponent;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 可以于ApplicationEvent、ApplicationLister配合使用
 * applicationContext.publishEvent() 发布事件
 * ApplicationLister.onApplicationEvent监听事件
 * @Author mk
 * @Date 2021/7/2 14:03
 * @Version 1.0
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    @Autowired
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext ags) throws BeansException {
        applicationContext = ags;
        getBeans();
    }

    public static void getBeans(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        //applicationContext.publishEvent();
        System.out.println(1);
    }
}
