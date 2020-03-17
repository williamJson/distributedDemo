package com.distributed.dao.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.distributed.dao.properties.DruidProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据库连接池配置文件
 */
@EnableConfigurationProperties(DruidProperties.class)
@Configuration
public class DruidConfig {


    @Autowired
    private DruidProperties properties;

    /**
     * 数据源
     *
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.configFromPropety(properties.toProperties());
        return dataSource;
    }


}
