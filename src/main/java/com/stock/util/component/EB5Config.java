package com.stock.util.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author makuo
 * @Date 2023/2/20 10:45
 **/
@Component
@Data
@ConfigurationProperties(prefix = "eb5")
public class EB5Config {

    private String[] ip;

    private String url;

    private String path;
}
