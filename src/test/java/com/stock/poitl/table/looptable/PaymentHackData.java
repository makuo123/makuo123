package com.stock.poitl.table.looptable;

import java.util.List;

public class PaymentHackData {
    private List<Goods> goods;
    private List<Labor> labors;
    private String total;
    private List<Goods> goods2;
    private List<Labor> labors2;

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setLabors(List<Labor> labors) {
        this.labors = labors;
    }

    public List<Labor> getLabors() {
        return labors;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setGoods2(List<Goods> goods2) {
        this.goods2 = goods2;
    }

    public List<Goods> getGoods2() {
        return goods2;
    }

    public void setLabors2(List<Labor> labors2) {
        this.labors2 = labors2;
    }

    public List<Labor> getLabors2() {
        return labors2;
    }
}
