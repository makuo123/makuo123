package com.stock.test.service.impl;

import com.stock.test.service.SameService;
import org.springframework.stereotype.Service;

@Service("SameServiceImpl2")
public class SameServiceImpl2 implements SameService {
    @Override
    public void save() {
        System.out.println("22222");
    }
}
