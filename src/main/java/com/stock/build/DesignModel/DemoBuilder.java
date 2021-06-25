package com.stock.build.DesignModel;

/**
 * 建造者模式
 *
 * @Author mk
 * @Date 2021/6/23 15:11
 * @Version 1.0
 */
public class DemoBuilder {
    private String id;
    private String name;
    private String content;

    public static DemoBuild builder(){
        return new DemoBuild();
    }

    private DemoBuilder(DemoBuild builder){
        this.id = builder.id;
        this.name = builder.name;
        this.content = builder.content;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    static class DemoBuild{
        private String id;
        private String name;
        private String content;

        public DemoBuild id(String id){
            this.id = id;
            return this;
        }

        public DemoBuild name(String name){
            this.name = name;
            return this;
        }

        public DemoBuild content(String content){
            this.content = content;
            return this;
        }

        public DemoBuilder build(){
            return new DemoBuilder(this);
        }
    }

}
