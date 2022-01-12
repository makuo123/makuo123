package com.stock;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableAdminServer
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.stock"})
public class StockApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(StockApplication.class, args);
        ConfigurableEnvironment environment = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        String path = environment.getProperty("server.servlet.context-path") == null ? "" : environment.getProperty("server.servlet.context-path");

        System.out.println("\n----------------------------------------------------------\n\t" +
                "Application UReport is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/ureport/designer\n\t" +
                "----------------------------------------------------------");

    }

    //加入如下配置
    @Bean({"threadPoolTaskExecutor", "webMvcAsyncTaskExecutor"})
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
}
