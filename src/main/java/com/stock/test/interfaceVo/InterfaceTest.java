package com.stock.test.interfaceVo;

/**
 * @Description
 * @Author makuo
 * @Date 2023/2/14 16:01
 **/
public class InterfaceTest {

    private static IEntity getName(){
        EntityImpl entity = new EntityImpl();
        entity.setName("zhangsan");
        return entity;
    }

    public static void main(String[] args) {
        IEntity name = getName();
        System.out.println(name.getName());
    }

}
