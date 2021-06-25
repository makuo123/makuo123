package com.stock.build.DesignModel.proxy;


import org.apache.commons.codec.binary.StringUtils;

/**
 * @Author mk
 * @Date 2021/6/25 14:56
 * @Version 1.0
 */
public class SfServer implements Server{
    @Override
    public String sendGoods(String name) {
        if (StringUtils.equals("sf", name)){
            return "顺丰快递";
        }else if (StringUtils.equals("yd", name)){
            return "韵达快递";
        }
        return "邮政快递";
    }
}
