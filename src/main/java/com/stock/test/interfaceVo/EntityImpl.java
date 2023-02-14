package com.stock.test.interfaceVo;

/**
 * @Description
 * @Author makuo
 * @Date 2023/2/14 15:55
 **/
public class EntityImpl implements IEntity {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
