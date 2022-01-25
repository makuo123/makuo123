package com.stock.util.spi;

import java.util.Arrays;

/**
 * 业务函数接口
 **/
public interface IFunction {

    /**
     * 函数名称
     **/
    public String getName();

    /**
     * 函数描述
     **/
    public String getDesc();

    /**
     * 函数运行异常时返回默认值
     **/
    public Object getDefVal();

    /**
     * 调用函数
     **/
    public Object process(Object... args) throws Exception;

    /**
     * 检查入参是否为空
     **/
    default boolean checkArgsIsEmpty(Object... args) {
        System.out.println(">> args=" + Arrays.toString(args));
        return args == null || args.length == 0;
    }
}
