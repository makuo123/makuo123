package com.stock.java8.defaults;

public interface DefaultInterface {
    public abstract void sayMethord();
    default public void sayHi(){
        System.out.println("sayHi");
    };
}
