package com.stock.build.DesignModel.factory;

/**
 * 工厂方法模式
 */
public abstract class WordParseBeanFactory {

    //------------------------------ WordParseBeanFactory

    //------------------------------ DataParsePolicy

    //------------------------------ CycleWordDataParse

    //------------------------------ CycleDataParsePolicy
    abstract DataParsePolicy createDataParsePolicy() ;

    DataParsePolicy getObject(){
        return this.createDataParsePolicy();
    }
    /*
    //简单工厂方法
    public static WordParseBean getWordParseBean(String type) {
        if ("doc".equals(type)) {
            return new WordParseDoc();
        } else if ("xls".equals(type)) {
            return new WordParseXls();
        } else {
            return null;
        }
    }
    */
}
