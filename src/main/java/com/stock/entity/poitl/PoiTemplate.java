package com.stock.entity.poitl;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class PoiTemplate {

    // 主键id
    private String id;

    private String tempPath;

    // 模板预填数据脚本
    private String tempSql;

    // 模板类型：文本、表格、图标、图片
    private String tempType;

    // 模板标签
    private String labor;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
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

    public String getLabor() {
        return labor;
    }

    public void setLabor(String labor) {
        this.labor = labor;
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
