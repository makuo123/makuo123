package com.stock.build.DesignModel.ResponseChain;

/**
 * @Author mk
 * @Date 2021/6/24 15:43
 * @Version 1.0
 */
public class IdCard extends AbstractDriveChain {
    public IdCard(AbstractDriveChain next) {
        super(next);
    }

    @Override
    public Boolean prepare(PrepareList prepareList) {
        if (prepareList.isIdCard()){
            System.out.println("已拿身份证");
            return true;
        }else {
            System.out.println("没身份证，不准开车");
            return false;
        }
    }
}
