package com.stock.util.spi;

import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class FunctionUtil {
    private static Map<String, IFunction> FUNCTIONS = null;

    protected FunctionUtil() {
    }

    private static Map<String, IFunction> getFunctions() {
        return FUNCTIONS;
    }

    /** call by CronJob.updateFunction() **/
    protected static synchronized void setFunctions(Map<String, IFunction> functions) {
        FUNCTIONS = functions;
    }

    /** load functions from jar file **/
    public static Map<String, IFunction> loadFunctions(URL jar) {
        Map<String, IFunction> functions = new ConcurrentHashMap<String, IFunction>();
        try {
            JarURLClassLoader classLoader = new JarURLClassLoader(jar);
            Set<Class> classes = classLoader.loadClass(IFunction.class, "com.example.function");
            if (classes != null && classes.size() > 0) {
                for (Class clazz : classes) {
                    IFunction function = (IFunction) clazz.newInstance();
                    String name = function.getName();
                    functions.put(name, function);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return functions;
    }

    private static IFunction getFunction(String name) {
        Map<String, IFunction> functions = getFunctions();
        if (functions == null || functions.size() == 0) {
            return null;
        }
        return functions.get(name);
    }

    /** call the function **/
    @SuppressWarnings("unchecked")
    public static <T> T call(String name, Object... args) {
        IFunction function = getFunction(name);
        if (function == null) {
            System.err.println("function \"" + name + "\" not exist!");
            return null;
        }
        try {
            return (T) function.process(args);
        } catch (Exception e) {
            e.printStackTrace();
            return (T) function.getDefVal();
        }
    }

}
