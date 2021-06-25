package com.stock.mapper;

import com.stock.po.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author mk
 * @Date 2020/10/21 14:20
 * @Version 1.0
 */
@Mapper
public interface StockMapper {
    void saveData(Stock stock);

    List<String> findStockList();

    void savaDataBatch(List<Stock> dataList);

    List<Stock> findStockEndPriceList(@Param("stockName") String stockName);

    String findMaxTrandeDate(@Param("name") String name);
}

