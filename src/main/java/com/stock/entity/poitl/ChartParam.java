package com.stock.entity.poitl;

import java.io.Serializable;

public class ChartParam implements Serializable {
    private static final long serialVersionUID = -2139944131426208763L;

    private String name;
    private Number value;

    private String param;

    public ChartParam() {
    }

    public ChartParam(String name, Number value) {
        this.name = name;
        this.value = value;
    }

    public ChartParam(String name, Number value, String param) {
        this.name = name;
        this.value = value;
        this.param = param;
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

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
