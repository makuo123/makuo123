package com.stock.util.logrecord;

import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author makuo
 * @Date 2023/6/14 16:40
 **/
@Service
public class UserService {
    private String name;
    @Loggable("修改用户信息")
    public void updateUser(String name, String address) {
        // 更新用户信息
    }
}
