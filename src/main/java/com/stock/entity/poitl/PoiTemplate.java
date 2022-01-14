package com.stock.entity.poitl;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class PoiTemplate {

    private String id;

    private String tempSql;

    private String tempType;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTempSql() {
        return tempSql;
    }

    public void setTempSql(String tempSql) {
        this.tempSql = tempSql;
    }

    public String getTempType() {
        return tempType;
    }

    public void setTempType(String tempType) {
        this.tempType = tempType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public static Boolean isNotBlack(PoiTemplate poiTemplate){
        if (poiTemplate != null && StringUtils.isNotBlank(poiTemplate.getTempType())){
            return true;
        }
        return false;
    }
}
