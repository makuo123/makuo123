package com.stock.service.sub;

/**
 * @Description
 * @Author makuo
 * @Date 2023/3/13 10:39
 **/
//@Service
public abstract class AuthToken implements AuthService{

    private final String name;

    private final String type;

    public AuthToken(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void grant(String name){
        System.out.println("AuthToken" + name);
    }
}
