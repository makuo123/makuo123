package com.stock.controller;

import com.stock.entity.Message;
import com.stock.po.Stock;
import com.stock.po.StockColletions;
import com.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author mk
 * @Date 2020/10/22 10:09
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/stock")
public class StockController {

    @Autowired
    StockService stockService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Message getStockList(@RequestParam(value = "stockName",required = false) String stockName){
        Message message = new Message();
        message.setFlag(Boolean.TRUE);

        List<String> stockList = stockService.findStockList();
        if (CollectionUtils.isEmpty(stockList)){
            message.setData(Collections.EMPTY_LIST);
            return message;
        }
        List<Stock> priceList = stockService.findStockEndPriceList(stockName);

        List<StockColletions> stockColletionsList = new ArrayList<>();
        for (String name : stockList) {
            List<Stock> collect = priceList.stream().filter(o -> o.getStockName().equals(name)).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(collect)){
                continue;
            }
            StockColletions stockColletions = new StockColletions(name,collect);
            stockColletionsList.add(stockColletions);
        }
        message.setData(CollectionUtils.isEmpty(stockColletionsList) ? Collections.EMPTY_LIST : stockColletionsList);
        log.info(message.getData().toString());
        return message;
    }

    @GetMapping(value = "/test")
    public void test(){
        log.debug("message0");
        log.info("message1");
        log.warn("message2");
        log.error("message3");
    }
}
