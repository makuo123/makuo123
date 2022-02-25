package com.stock.sameservice;

import com.stock.StockApplication;
import com.stock.test.service.SameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StockApplication.class)
public class Same {

    @Resource(name = "SameServiceImpl2")
    SameService sameService;

    @Test
    public void test(){
        sameService.save();
    }
}
