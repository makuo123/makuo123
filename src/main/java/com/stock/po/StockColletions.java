package com.stock.po;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mk
 * @Date 2020/10/22 10:44
 * @Version 1.0
 */
public class StockColletions {

    private String name;
    private List<Stock> list = new ArrayList<>();

    public StockColletions() {
    }

    public StockColletions(String name, List<Stock> list) {
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Stock> getList() {
        return list;
    }

    public void setList(List<Stock> list) {
        this.list = list;
    }
}
