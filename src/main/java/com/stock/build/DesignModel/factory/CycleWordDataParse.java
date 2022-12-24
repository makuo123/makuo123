package com.stock.build.DesignModel.factory;

public class CycleWordDataParse extends WordParseBeanFactory{
    @Override
    DataParsePolicy createDataParsePolicy() {
        return new CycleDataParsePolicy();
    }
}
