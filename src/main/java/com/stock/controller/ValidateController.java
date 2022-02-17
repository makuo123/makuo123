package com.stock.controller;

import com.stock.util.volidator.NotConflictUser;
import com.stock.util.volidator.UniqueUser;
import com.stock.util.volidator.User;
import com.stock.util.volidator.UserRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
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
}

