package com.stock.test.service.impl;

import com.stock.test.service.SameService;
import org.springframework.stereotype.Service;

@Service("SameServiceImpl")
//@Primary
public class SameServiceImpl implements SameService {
    @Override
    public void save() {
        System.out.println("111111");
    }
}
