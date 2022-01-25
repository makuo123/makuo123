package com.stock.util.spi;

import java.util.Base64;

public class Base64Encode implements IFunction {

    @Override
    public String getName() {
        return "base64Encode";
    }

    @Override
    public String getDesc() {
        return "Base64加密";
    }

    @Override
    public Object getDefVal() {
        return "";
    }

    @Override
    public Object process(Object... args) throws Exception {
        if (checkArgsIsEmpty(args)) {
            return "";
        }
        String s = (String) args[0];
        return Base64.getEncoder().encodeToString(s.getBytes());
    }

}
