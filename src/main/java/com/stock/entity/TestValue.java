package com.stock.entity;

/**
 * @Author mk
 * @Date 2021/6/9 11:12
 * @Version 1.0
 */
public class TestValue {

    private String code;
    private String value;

    public TestValue() {
    }

    public TestValue(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
