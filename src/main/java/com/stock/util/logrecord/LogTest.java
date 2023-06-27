package com.stock.util.logrecord;

/**
 * @Description
 * @Author makuo
 * @Date 2023/6/14 16:40
 **/
public class LogTest {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.updateUser("张三", "北京市朝阳区");
        //LogProcessor.process(userService, UserService.class.getDeclaredMethods()[0], new Object[] {"张三", "北京市朝阳区"});
    }
}
