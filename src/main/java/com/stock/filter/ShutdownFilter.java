package com.stock.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @date : 23/04/2023
 * @description: ShutdownFilter
 * 实现无感上下线.
 * 机器正常关闭前，优先处理完未完成的任务，同时不再接收新的请求
 **/
@ConditionalOnProperty(prefix = "apocalypse.security", name = "graceful-shutdown", havingValue = "true", matchIfMissing = true)
//@ApocalypseWebFilter
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ShutdownFilter implements SmartLifecycle, WebFilter, InitializingBean {

    private final Logger logger;

    private int shutdownTimeout;

    private volatile boolean running = true;

    private final ConfigurableApplicationContext configurableApplicationContext;

    public ShutdownFilter(ConfigurableApplicationContext configurableApplicationContext) {
        this.configurableApplicationContext = configurableApplicationContext;
        logger = LoggerFactory.getLogger(ShutdownFilter.class);
    }

    /**
     * 通过Q-config动态配置超时时长，判断0作为兜底方案
     */
    private int getShutdownTimeout() {
        if (0 == shutdownTimeout) {
            shutdownTimeout = 30000;
        }
        return shutdownTimeout;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    /**
     * 通过SpringBoot在JVM注册的shutdown hook完成
     * Bean会往JVM中注册shutdown tasks，在机器即将关闭时清理掉这些任务
     * 在SpringBoot 2.3.0中可以直接通过application.properties实现
     */
    @SuppressWarnings("all")
    @Override
    public void stop(Runnable callback) {
        logger.info("Machine is going to close, finishing all tasks in {} seconds", getShutdownTimeout());
        stop();
        callback.run();
        try {
            Thread.sleep(getShutdownTimeout());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Shut down successfully");
    }

    @Override
    public void start() {
        running = true;
    }

    @Override
    public void stop() {
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPhase() {
        return SmartLifecycle.super.getPhase();
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return running ? chain.filter(exchange) : writeNoPermissionMessage(exchange);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(configurableApplicationContext, "ConfigurableApplicationContext is not empty");
        configurableApplicationContext.registerShutdownHook();
        configurableApplicationContext.addApplicationListener((ApplicationListener<ContextClosedEvent>) event -> this.stop(() -> {
        }));
    }

    private Mono<Void> writeNoPermissionMessage(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
        return response.writeWith(Mono.just(response.bufferFactory().wrap("Temporarily not providing services".getBytes(StandardCharsets.UTF_8))));
    }
}


