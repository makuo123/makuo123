package com.stock.util.noifelse;

/**
 * @Description
 * @Author makuo
 * @Date 2023/3/22 16:12
 **/
public enum TypeEnum implements Action{
    TUANG{
        @Override
        public void buy() {
            System.out.println("buy tuang");
        }
    },TUYUE{
        @Override
        public void buy() {
            System.out.println("buy tuyue");
        }
    },HUIANG{
        @Override
        public void buy() {
            System.out.println("buy huiang");
        }
    };

    public static void main(String[] args) {
        TypeEnum type = TypeEnum.valueOf("TUYUE");
        type.buy();
        TypeEnum[] values = TypeEnum.values();
        for (TypeEnum value : values) {
            value.buy();
        }
    }

}
