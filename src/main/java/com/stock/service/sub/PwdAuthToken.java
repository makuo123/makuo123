package com.stock.service.sub;

/**
 * @Description
 * @Author makuo
 * @Date 2023/3/13 10:47
 **/
public class PwdAuthToken implements AuthService{
    @Override
    public void grant(String name) {
        System.out.println("PwdAuthToken" + name);
    }
}
