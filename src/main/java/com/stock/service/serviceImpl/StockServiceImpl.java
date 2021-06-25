package com.stock.service.serviceImpl;

import com.stock.po.Stock;
import com.stock.service.StockService;
import com.stock.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author mk
 * @Date 2020/10/21 14:15
 * @Version 1.0
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockMapper stockMapper;

    @Transactional
    @Override
    public void SaveDate(Stock stock) {
        stockMapper.saveData(stock);
    }

    @Override
    public List<String> findStockList() {
        return stockMapper.findStockList();
    }

    @Override
    public void savaDataBatch(List<Stock> dataList) {
        stockMapper.savaDataBatch(dataList);
    }

    @Override
    public List<Stock> findStockEndPriceList(String stockName) {
        return stockMapper.findStockEndPriceList(stockName);
    }

    @Override
    public String findMaxTrandeDate(String name) {
        return stockMapper.findMaxTrandeDate(name);
    }
}
