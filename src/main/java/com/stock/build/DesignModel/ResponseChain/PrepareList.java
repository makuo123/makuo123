package com.stock.build.DesignModel.ResponseChain;

/**
 * @Author mk
 * @Date 2021/6/24 15:13
 * @Version 1.0
 */
public class PrepareList {
    private boolean buyCar;
    private boolean idCard;
    private boolean license;

    public boolean isBuyCar() {
        return buyCar;
    }

    public void setBuyCar(boolean buyCar) {
        this.buyCar = buyCar;
    }

    public boolean isIdCard() {
        return idCard;
    }

    public void setIdCard(boolean idCard) {
        this.idCard = idCard;
    }

    public boolean isLicense() {
        return license;
    }

    public void setLicense(boolean license) {
        this.license = license;
    }
}
