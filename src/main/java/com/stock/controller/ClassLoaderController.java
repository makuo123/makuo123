package com.stock.controller;


/*
 * 	此源代码为北京圣博润高新技术股份有限公司资产，非北京圣博润
 * 高新技术股份有限公司公司员工严禁保留、拷贝、修改此代码。
 *
 * Copyright 北京圣博润高新技术股份有限公司. All rights reserved.
 */

/*
 * @ClassName:ClassLoaderController
 * @Description TODO
 * @Author 曹传红
 * @Time 2019-06-18 16:16
 */

import com.stock.util.JarLoader;
import com.stock.util.spi.ModuleClassLoader;
import com.stock.util.spi.SpringContextUtil;
import com.stock.util.spi.classloader.ClassloaderResponsity;
import org.hc.service.IReportDataService;
import org.hc.utils.JDBCUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/loader")
public class ClassLoaderController {


    @GetMapping(value = "/beans")
    public List<Map<String, Object>> beans(){
        return SpringContextUtil.getAllBean();
    }

    @GetMapping(value = "/deleteModule")
    public List<Map<String, Object>> deleteModule(String moduleName){
        if(ClassloaderResponsity.getInstance().containsClassLoader(moduleName)){
            ClassloaderResponsity.getInstance().removeClassLoader(moduleName);
        }
        return beans();
    }

    @GetMapping(value = "/loadJar")
    public Object loadJar(String jarPath) {
        File jar = new File(jarPath);
        URI uri = jar.toURI();
        String moduleName = jarPath.substring(jarPath.lastIndexOf("/")+1,jarPath.lastIndexOf("."));
        try {

            if(ClassloaderResponsity.getInstance().containsClassLoader(moduleName)){
                ClassloaderResponsity.getInstance().removeClassLoader(moduleName);
            }

            ModuleClassLoader classLoader = new ModuleClassLoader(new URL[]{uri.toURL()}, Thread.currentThread().getContextClassLoader());
            SpringContextUtil.getBeanFactory().setBeanClassLoader(classLoader);
            Thread.currentThread().setContextClassLoader(classLoader);
            classLoader.initBean();
            ClassloaderResponsity.getInstance().addClassLoader(moduleName,classLoader);
            IReportDataService reportDataImpl = (IReportDataService)SpringContextUtil.getBean("reportDataImpl");
            Connection connection = null;
            try {
                connection = JDBCUtils.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return reportDataImpl.collectData(connection, null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/invoke")
    public Object invokeBean(String beanName){
        Method method = ReflectionUtils.findMethod(SpringContextUtil.getBean(beanName).getClass(), "users");
        Object result = ReflectionUtils.invokeMethod(method, SpringContextUtil.getBean(beanName));
        return result;
    }


    @GetMapping(value = "/loadjar/export")
    public void exportByLoadJar(String beanName){
        String path = "C:\\Users\\makuo\\Desktop\\workspace\\jar2demo\\out\\artifacts\\jar2demo_jar\\jar2demo.jar";
        JarLoader loader = new JarLoader();
        loader.loadJars(path);
        loader.execute("jar2demo.jar", "com.jar.Calculator", "addNumbers", new Integer[]{1, 2, 3});
        loader.unloadJar("jar2demo.jar");
    }
}
