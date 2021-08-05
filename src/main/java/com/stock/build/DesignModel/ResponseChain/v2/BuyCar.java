package com.stock.build.DesignModel.ResponseChain.v2;

import com.stock.build.DesignModel.ResponseChain.AbstractDriveChain;
import com.stock.build.DesignModel.ResponseChain.PrepareList;

/**
 *  * ==================
 *  * 具体责任对象
 *  * ==================
 * @Author mk
 * @Date 2021/6/24 15:39
 * @Version 1.0
 */
public class BuyCar implements DrivePrepareFilter {

    @Override
    public void doFilter(PrepareList prepareList, FilterChain filterChain) {
        if (prepareList.isBuyCar()) {
            System.out.println("已买车");
            filterChain.doFilter(prepareList, filterChain);
        }
    }
}
