package com.stock.build.DesignModel.ResponseChain.v2;

import com.stock.build.DesignModel.ResponseChain.AbstractDriveChain;
import com.stock.build.DesignModel.ResponseChain.PrepareList;

/**
 * ==================
 * 具体责任对象
 * ==================
 * @Author mk
 * @Date 2021/6/24 15:43
 * @Version 1.0
 */
public class IdCard implements DrivePrepareFilter {

    @Override
    public void doFilter(PrepareList prepareList, FilterChain filterChain) {
        if (prepareList.isIdCard()){
            System.out.println("已拿身份证");
            filterChain.doFilter(prepareList, filterChain);
        }
    }
}
