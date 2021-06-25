package com.stock.build.DesignModel.ResponseChain.v2;

import com.stock.build.DesignModel.ResponseChain.AbstractDriveChain;
import com.stock.build.DesignModel.ResponseChain.PrepareList;

/**
 * @Author mk
 * @Date 2021/6/24 15:46
 * @Version 1.0
 */
public class License implements DrivePrepareFilter {

    @Override
    public void doFilter(PrepareList prepareList, FilterChain filterChain) {
        if (prepareList.isLicense()){
            System.out.println("已获取驾照");
            filterChain.doFilter(prepareList, filterChain);
        }
    }
}
