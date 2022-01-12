package com.stock.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.65.129", 9200, "http")

                        /** 多个节点也是在当前地方配置 */
//                        , new HttpHost("localhost", 9300, "http")
                ));
        StringUtils.isEmpty("");
        return client;
    }
}

