package com.stock.build.DesignModel.ResponseChain.v2;

import com.stock.build.DesignModel.ResponseChain.DriveCar;
import com.stock.build.DesignModel.ResponseChain.PrepareList;

/**
 * 责任链模式--升级版
 * @Author mk
 * @Date 2021/6/24 16:35
 * @Version 1.0
 */
public interface DrivePrepareFilter {
    void doFilter(PrepareList prepareList, FilterChain filterChain);
}
