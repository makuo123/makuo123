package com.stock.entity.poitl;

import java.io.Serializable;

public class ChartParam implements Serializable {
    private static final long serialVersionUID = -2139944131426208763L;

    private String name;
    private Number value;

    public ChartParam() {
    }

    public ChartParam(String name, Number value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }
}
