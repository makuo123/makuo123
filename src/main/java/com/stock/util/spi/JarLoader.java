package com.stock.util.spi;


import com.stock.util.SpringContextUtil;
import com.stock.util.spi.classloader.ClassloaderResponsity;
import org.hc.service.IReportDataService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

public class JarLoader {

    Map<String, URLClassLoader> jarLoaders = new HashMap<>();

    public void loadJars(String path){

        File file = new File(path);
        loadJars(file);

    }

    public void loadJars(File file){
        System.out.println(new File(".").getAbsoluteFile());

        try {
            if (file.isDirectory()) {
                for (File listFile : file.listFiles()) {
                    String name = listFile.getName();
                    URLClassLoader loader = new URLClassLoader(new URL[]{listFile.toURI().toURL()});
                    jarLoaders.put(name, loader);
                }
            }else{
                URLClassLoader classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()});
                jarLoaders.put(file.getName(), classLoader);
            }

        }catch (Exception e){
            System.out.println("load jar file error!");
        }
    }

    public void unloadJar(String jarName){
        URLClassLoader loader = jarLoaders.get(jarName);
        jarLoaders.remove(jarName);
        try {
            loader.close();
        }catch (Exception e){
            System.out.println("Unload jar " + jarName + " error!");
        }

    }

    public void execute(String jarFileName, String reference, String methodName, String params){
        /*List<Class> classes = new ArrayList<>();
        List<Integer> p = new ArrayList<>();
        Class[] classList = new Class[params.length];
        int[] pList = new int[params.length];*/

        /*for (Object param : params) {
            Class paramClass = param.getClass();
            classes.add(paramClass);
            p.add(Integer.parseInt(String.valueOf(param)));
        }

        for (int i = 0; i < params.length; i++) {
            classList[i] = params[i].getClass();
            pList[i] = Integer.parseInt(String.valueOf(params[i]));
        }*/

        try {
            URLClassLoader urlClassLoader = jarLoaders.get(jarFileName);
            Class aClass = urlClassLoader.loadClass(reference);
            IReportDataService cal = (IReportDataService)aClass.newInstance();
            //System.out.println(cal.addNumbers(params));
            //Method method = aClass.getMethod(methodName, classList);
            //Object res = method.invoke(method, params);
            //System.out.println(cal.collectData(params, null));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Execute error!");
        }
    }

    public static void main(String[] args) throws Exception{
        String jarPath = "D:\\\\project\\\\report-collect\\\\out\\\\artifacts\\\\report_collect_jar\\\\report-collect.jar";
        File file = new File(jarPath);
        String moduleName = jarPath.substring(jarPath.lastIndexOf(File.separator)+1,jarPath.lastIndexOf("."));
        try {

            if(ClassloaderResponsity.getInstance().containsClassLoader(moduleName)){
                ClassloaderResponsity.getInstance().removeClassLoader(moduleName);
            }

        ModuleClassLoader moduleClassLoader = new ModuleClassLoader(new URL[]{file.toURI().toURL()}, Thread.currentThread().getContextClassLoader());
//        moduleClassLoader.loadClass();
        SpringContextUtil.getBeanFactory().setBeanClassLoader(moduleClassLoader);
        Thread.currentThread().setContextClassLoader(moduleClassLoader);
        moduleClassLoader.initBean();
        Object reportDataImpl = SpringContextUtil.getBean("reportDataImpl");
        JarLoader loader = new JarLoader();
        loader.loadJars(jarPath);
        loader.execute("report-collect.jar", "com.hc.data.ReportDataImpl", "collectData", "1");
        loader.unloadJar("report-collect.jar");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*String path = "D:\\project\\report-collect\\out\\artifacts\\report_collect_jar\\report-collect.jar";
        JarLoader loader = new JarLoader();
        loader.loadJars(path);
        loader.execute("report-collect.jar", "com.hc.data.ReportDataImpl", "collectData", "1");
        loader.unloadJar("report-collect.jar");*/
    }
}
