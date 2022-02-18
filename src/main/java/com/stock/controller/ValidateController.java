package com.stock.controller;

import com.stock.test.async.AsyncService;
import com.stock.util.volidator.tenant.SameTenant;
import com.stock.util.volidator.unique.NotConflictUser;
import com.stock.util.volidator.unique.UniqueUser;
import com.stock.util.volidator.unique.User;
import com.stock.util.volidator.unique.UserRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/validate")
@Slf4j
@Validated
public class ValidateController {
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/save")
    public User createUser(@UniqueUser @Valid User user){
        User savedUser = userRepository.save(user);
        log.info("save user id is {}",savedUser.getId());
        return savedUser;
    }

    @SneakyThrows
    @PutMapping("save1")
    public User updateUser(@NotConflictUser @Valid @RequestBody User user){
        User editUser = userRepository.save(user);
        log.info("update user is {}",editUser);
        return editUser;
    }

    @GetMapping("teant")
    public void volidatorTeant(@SameTenant Long str){
        System.out.println("ssdf");
    }

    @Autowired
    private AsyncService asyncService;
    @GetMapping("async")
    public void asyncTest(){
        asyncService.test1();
        asyncService.test2();
        System.out.println("ssdf");
    }
}

