package com.stock.config;

/*import com.bstek.ureport.console.UReportServlet;
import com.bstek.ureport.definition.datasource.BuildinDatasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ImportResource("classpath:ureport-console-context.xml")
@Configuration
public class UreportConfig implements BuildinDatasource {

    @Resource
    DataSource dataSource;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    public ServletRegistrationBean buildUreportServlet(){
        return new ServletRegistrationBean(new UReportServlet(),"/ureport/*");
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public Connection getConnection() {

        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error("ureport 数据源 连接失败！");
            e.printStackTrace();
        }
        return null;
    }
}*/
