package com.stock.build.DesignModel.ResponseChain;

/**
 * @Author mk
 * @Date 2021/6/24 15:46
 * @Version 1.0
 */
public class License extends AbstractDriveChain {
    public License(AbstractDriveChain next) {
        super(next);
    }

    @Override
    public Boolean prepare(PrepareList prepareList) {
        if (prepareList.isLicense()){
            System.out.println("已拿驾照");
            return true;
        }else {
            System.out.println("没驾照，不准开车");
            return false;
        }
    }
}
