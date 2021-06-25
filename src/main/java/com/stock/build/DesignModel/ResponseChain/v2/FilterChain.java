package com.stock.build.DesignModel.ResponseChain.v2;

import com.stock.build.DesignModel.ResponseChain.DriveCar;
import com.stock.build.DesignModel.ResponseChain.PrepareList;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mk
 * @Date 2021/6/24 16:40
 * @Version 1.0
 */
public class FilterChain implements DrivePrepareFilter{

    private int count = 0;

    private DriveCar driveCar;

    private List<DrivePrepareFilter> drivePrepareFilterList;

    public FilterChain(DriveCar driveCar) {
        this.driveCar = driveCar;
    }

    public void addFilter(DrivePrepareFilter drivePrepareFilter){

        if (drivePrepareFilterList == null){
            drivePrepareFilterList = new ArrayList<>();
        }

        drivePrepareFilterList.add(drivePrepareFilter);
    }

    @Override
    public void doFilter(PrepareList prepareList, FilterChain filterChain) {
        if (drivePrepareFilterList.size() == count){
            driveCar.drive();
        }else {
            drivePrepareFilterList.get(count++).doFilter(prepareList, filterChain);
        }
    }


}
