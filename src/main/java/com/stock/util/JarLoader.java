package com.stock.util;

import com.superface.ICalculator;

import java.io.File;
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

    public void execute(String jarFileName, String reference, String methodName, Integer[] params){
        //List<Class> classes = new ArrayList<>();
        //List<Integer> p = new ArrayList<>();
        //Class[] classList = new Class[params.length];
        //int[] pList = new int[params.length];

        /*for (Object param : params) {
            Class paramClass = param.getClass();
            classes.add(paramClass);
            p.add(Integer.parseInt(String.valueOf(param)));
        }*/

        /*for (int i = 0; i < params.length; i++) {
            classList[i] = params[i].getClass();
            //pList[i] = Integer.parseInt(String.valueOf(params[i]));
        }*/

        try {
            URLClassLoader urlClassLoader = jarLoaders.get(jarFileName);
            Class aClass = urlClassLoader.loadClass(reference);
            ICalculator cal = (ICalculator)aClass.newInstance();
            //System.out.println(cal.addNumbers(params));
            //Method method = aClass.getMethod(methodName, classList);
            //Object res = method.invoke(method, params);
            System.out.println(cal.addNumbers(params));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Execute error!");
        }
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\makuo\\Desktop\\workspace\\jar2demo\\out\\artifacts\\jar2demo_jar\\jar2demo.jar";
        JarLoader loader = new JarLoader();
        loader.loadJars(path);
        loader.execute("jar2demo.jar", "com.jar.Calculator", "addNumbers", new Integer[]{1, 2, 3});
        loader.unloadJar("jar2demo.jar");
    }
}
