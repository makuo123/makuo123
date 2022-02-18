package com.stock.util.volidator.unique;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserRepository {
    public boolean existsByUserNameOrEmailOrTelphone(String userName, String email, String telphone) {
        return true;
    }

    public Collection<User> findByUserNameOrEmailOrTelphone(String userName, String email, String telphone) {
        return null;
    }

    public User save(User user) {
        return null;
    }
}
