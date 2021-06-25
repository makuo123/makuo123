package com.stock.build.DesignModel.proxy;

/**
 * @Author mk
 * @Date 2021/6/25 15:02
 * @Version 1.0
 */
public class ServerProxy implements Server{

    private Server server;

    public ServerProxy(Server server) {
        this.server = server;
    }

    @Override
    public String sendGoods(String name) {
        return server.sendGoods(name);
    }
}
