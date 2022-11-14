package com.haha.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceWrapper;
import com.haha.ds.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 但数据源druid配置
 */
@Configuration
@ConditionalOnProperty(value = "spring.datasource.single", havingValue = "true")
public class SingleDruidConfig {

    /**
     * 创建名为 masterDataSource 的数据源
     */
    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource masterDataSource() {
        return new DruidDataSourceWrapper();
    }

    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put("master", masterDataSource);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        // 设置默认数据源
        dataSource.setDefaultTargetDataSource(masterDataSource);

        return dataSource;
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource(masterDataSource()));
    }
}
