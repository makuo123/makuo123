package com.stock.build.DesignModel;

import com.stock.build.DesignModel.ResponseChain.*;
import com.stock.build.DesignModel.ResponseChain.v2.FilterChain;
import com.stock.build.DesignModel.adapter.ObjectAdaptee;
import com.stock.build.DesignModel.adapter.ObjectAdapter;
import com.stock.build.DesignModel.adapter.ObjectAdapter2;
import com.stock.build.DesignModel.observer.ConcreteObserver;
import com.stock.build.DesignModel.observer.ConcreteSubject;
import com.stock.build.DesignModel.observer.Observer;
import com.stock.build.DesignModel.observer.Subject;
import com.stock.build.DesignModel.proxy.Server;
import com.stock.build.DesignModel.proxy.ServerInvocationHandler;
import com.stock.build.DesignModel.proxy.ServerProxy;
import com.stock.build.DesignModel.proxy.SfServer;
import com.stock.build.DesignModel.wrapper.ChineseCooker;
import com.stock.build.DesignModel.wrapper.CookDinner;
import com.stock.build.DesignModel.wrapper.CutCooker;
import com.stock.build.DesignModel.wrapper.DrinkCooker;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @Author mk
 * @Date 2021/6/23 15:25
 * @Version 1.0
 */
public class TestBuilder {

    /** 建造者模式 */
    @Test
    public void test(){
        DemoBuilder build = DemoBuilder.builder()
                .id("1")
                .name("mark")
                .content("建造者模式")
                .build();
        System.out.println(build.toString());
    }

    /** 单例模式 */
    @Test
    public void test2(){
        DemoSingleton instance = DemoSingleton.getInstance();
        DemoSingleton instance1 = DemoSingleton.getInstance();
        System.out.println(instance == instance1);
    }

    /** 适配器模式 */
    @Test
    public void test3(){
        ObjectAdapter objectAdapter = new ObjectAdapter(new ObjectAdaptee());
        objectAdapter.mehtod2();
        objectAdapter.method1();

        ObjectAdapter2 objectAdapter2 = new ObjectAdapter2();
        objectAdapter2.method2();
        objectAdapter2.method1();
    }

    /** 装饰器模式 */
    @Test
    public void test4(){
        CookDinner cutCooker = new CutCooker(new ChineseCooker());
        CookDinner drinkCooker = new DrinkCooker(new ChineseCooker());
        cutCooker.cook();
        drinkCooker.cook();
    }

    /** 责任链模式 简易版*/
    @Test
    public void test5(){
        PrepareList filterList = new PrepareList();
        filterList.setBuyCar(false);
        filterList.setIdCard(true);
        filterList.setLicense(false);
        AbstractDriveChain buyCar = new BuyCar(null);
        AbstractDriveChain idCard = new IdCard(buyCar);
        AbstractDriveChain license = new License(idCard);
        license.doFilter(filterList, new DriveCar());
    }

    /** 责任链模式 升级版*/
    @Test
    public void test6(){
        PrepareList prepareList = new PrepareList();
        prepareList.setBuyCar(true);
        prepareList.setIdCard(true);
        prepareList.setLicense(true);

        FilterChain filterChain = new FilterChain(new DriveCar());
        filterChain.addFilter(new com.stock.build.DesignModel.ResponseChain.v2.BuyCar());
        filterChain.addFilter(new com.stock.build.DesignModel.ResponseChain.v2.IdCard());
        filterChain.addFilter(new com.stock.build.DesignModel.ResponseChain.v2.License());

        filterChain.doFilter(prepareList, filterChain);
    }

    /** 观察者模式 */
    @Test
    public void test7(){
        ConcreteSubject subject = new ConcreteSubject();

        Observer observer = new ConcreteObserver();
        subject.addObserver(observer);
        Observer observer1 = new ConcreteObserver();
        subject.addObserver(observer1);
        subject.change("new state");

    }

    /** 代理模式 */
    @Test
    public void test8(){
        ServerProxy serverProxy = new ServerProxy(new SfServer());
        System.out.println(serverProxy.sendGoods("ysd"));
    }

    /** 代理模式 invocationHandler 动态代理*/
    @Test
    public void test9(){
        ServerInvocationHandler serverInvocationHandler = new ServerInvocationHandler(new SfServer());
        Server o = (Server)Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{Server.class}, serverInvocationHandler);
        System.out.println(o.sendGoods("sf"));
    }
}
