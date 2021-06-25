package com.stock.build.DesignModel.ResponseChain;

/**
 * @Author mk
 * @Date 2021/6/24 15:09
 * @Version 1.0
 */
public abstract class AbstractDriveChain {

    private AbstractDriveChain next;

    public AbstractDriveChain(AbstractDriveChain next) {
        this.next = next;
    }

    public void doFilter(PrepareList prepareList, DriveCar driveCar){
        Boolean prepare = prepare(prepareList);
        if (!prepare){
            return;
        }

        if (next == null){
            driveCar.drive();
        }else {
            next.doFilter(prepareList, driveCar);
        }
    }

    protected abstract Boolean prepare(PrepareList prepareList);
}
