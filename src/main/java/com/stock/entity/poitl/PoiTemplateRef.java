package com.stock.entity.poitl;

public class PoiTemplateRef {

    private String id;
    private String refTempSql;
    private String tempInfofId;
    private Integer dataType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefTempSql() {
        return refTempSql;
    }

    public void setRefTempSql(String refTempSql) {
        this.refTempSql = refTempSql;
    }

    public String getTempInfofId() {
        return tempInfofId;
    }

    public void setTempInfofId(String tempInfofId) {
        this.tempInfofId = tempInfofId;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }
}
