package com.stock.controller;

import com.stock.entity.Message;
import com.stock.po.Stock;
import com.stock.po.StockColletions;
import com.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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

//        Hashtable
//        ConcurrentHashMap


    }

   /* public static void main(String[] args) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date parse = sf.parse("2019-07-13");
            Date parse1 = sf.parse("2021-12-08");
            long time = parse1.getTime() - parse.getTime();
            System.out.println(time/1000/60/60/24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document parse = db.parse("src/main/resources/xml/test.xml");
            Element documentElement = parse.getDocumentElement();
//            String tagName = documentElement.getTagName();
            NodeList nodeList = documentElement.getElementsByTagName("student");
            NamedNodeMap attributes = documentElement.getAttributes();
            System.out.println(1);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (SAXException saxException){
            saxException.printStackTrace();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }

    }
}
