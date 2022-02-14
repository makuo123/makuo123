package com.stock.entity.poitl;

import java.util.List;
import java.util.Map;

public class PoiTemplateRefData {

    private Integer dataType;
    private List<Map<String,Object>> data;

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }
}
