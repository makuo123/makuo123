package com.stock.schedule;

import com.alibaba.fastjson.JSONObject;
import com.stock.po.Stock;
import com.stock.service.StockService;
import com.stock.util.DateUtil;
import com.stock.util.HttpClientUtil;
import com.stock.util.JsonListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Author mk
 * @Date 2020/10/21 13:56
 * @Version 1.0
 */
@Component
public class StockStaticCount {
    /**根据股票名称查询代码*/
    private static final String CODE_URL = "http://tool.cnfunny.cn/api/stock/base?keyword=";
    /**根据股票代码查询数据*/
    private static final String VALUE_URL = "http://tool.cnfunny.cn/api/stock/list?code=";
    @Autowired
    StockService stockService;

    @Scheduled(cron = "0 15 11 ? * *")
    public void countData(){

        //查询股票名称
        List<String> nameList = stockService.findStockList();
        for (String name : nameList) {
            String maxDate = stockService.findMaxTrandeDate(name);
            String startDate = "20140101";
            if (!StringUtils.isEmpty(maxDate)){
                startDate = DateUtil.addDay(maxDate, 1);
            }
            String endDate = DateUtil.addDay(startDate, 19);
            //根据股票名称查询代码
            String url = CODE_URL + name;
            String s1 = HttpClientUtil.doGet(url);
            Map object1 = JsonListUtil.getObject(s1, Map.class);
            List<Map<String, Object>> list1 = (List<Map<String, Object>>) object1.get("label");
            String tsCode = String.valueOf(list1.get(0).get("value"));

            while (Integer.parseInt(endDate) <= DateUtil.getNowDate()) {

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(VALUE_URL)
                        .append(tsCode)
                        .append("&invite_code=&start=")
                        .append(startDate)
                        .append("&end=")
                        .append(endDate)
                        .append("&type=ts_code");
                String s = HttpClientUtil.doGet(stringBuilder.toString());

                //重置时间
                startDate = DateUtil.addDay(endDate, 1);
                endDate = DateUtil.addDay(startDate, 19);

                if (StringUtils.isEmpty(s)){continue;}
                Map object = JsonListUtil.getObject(s, Map.class);
                Object list = object.get("list");

                List<JSONObject> objects = JsonListUtil.jsonToList(list.toString(), JSONObject.class);

                List<Stock> dataList = new ArrayList<>();
                for (JSONObject o : objects) {
                    Stock stock = new Stock();
                    stock.setStockName(String.valueOf(list1.get(0).get("label")));
                    stock.setStockCode(o.getString("ts_code"));
                    stock.setTradeTime(o.getString("trade_date"));
                    stock.setEndPrice(o.getBigDecimal("close"));
                    stock.setStartPrice(o.getBigDecimal("open"));
                    stock.setCreate_time(new Date());
                    //stockService.SaveDate(stock);
                    dataList.add(stock);
                }
                if (CollectionUtils.isEmpty(dataList)){
                    continue;
                }
                stockService.savaDataBatch(dataList);


            }
        }
    }

    public static void main(String[] args) {
        String url = "http://tool.cnfunny.cn/api/stock/base?keyword=%E7%B4%AB%E9%87%91%E7%9F%BF%E4%B8%9A";
        String s1 = HttpClientUtil.doGet(url);
        String s = HttpClientUtil.doGet("http://tool.cnfunny.cn/api/stock/list?code=601899.SH&invite_code=&start=20201002&end=20201020&type=ts_code");
        System.out.println(s);
        Map object = JsonListUtil.getObject(s, Map.class);
        Object list = object.get("list");
        List<JSONObject> objects = JsonListUtil.jsonToList(list.toString(), JSONObject.class);
        System.out.println(1111);
    }
}
