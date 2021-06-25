package com.stock.service;

import com.stock.po.Stock;

import java.util.List;

/**
 * @Author mk
 * @Date 2020/10/21 14:15
 * @Version 1.0
 */
public interface StockService {
    void SaveDate(Stock stock);

    List<String> findStockList();

    void savaDataBatch(List<Stock> dataList);

    List<Stock> findStockEndPriceList(String stockName);

    String findMaxTrandeDate(String name);
}
