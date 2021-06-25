package com.stock.build.DesignModel.ResponseChain;

/**
 * @Author mk
 * @Date 2021/6/24 15:39
 * @Version 1.0
 */
public class BuyCar extends AbstractDriveChain {
    public BuyCar(AbstractDriveChain next) {
        super(next);
    }

    @Override
    public Boolean prepare(PrepareList prepareList) {
        if (prepareList.isBuyCar()){
            System.out.println("车已买");
            return true;
        }else {
            System.out.println("车都没买，不准开车");
            return false;
        }
    }
}
