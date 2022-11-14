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
 * 多数据源druid配置类
 */
@Configuration
@ConditionalOnProperty(value = "spring.datasource.multi", havingValue = "true")
public class MultiDruidConfig {

    /**
     * 创建名为 masterDataSource 的数据源
     */
    @Bean(name = "masterDataSource")
    @Primary    //该注解的作用是，当有多个相同的Bean的时候，优先选择有该注解的Bean 配置多数据源，必须有一个主数据源
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource masterDataSource() {
        return new DruidDataSourceWrapper();
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource(masterDataSource(), slaverDataSource()));
    }

    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                               @Qualifier("slaverDataSource") DataSource slaverDataSource) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put("master", masterDataSource);
        targetDataSource.put("slaver", slaverDataSource);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        // 设置默认数据源
        dataSource.setDefaultTargetDataSource(masterDataSource);

        return dataSource;
    }

    /**
     * 创建名为 slaverDataSource 的数据源
     */
    @Bean(name = "slaverDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slaver")
    public DataSource slaverDataSource() {
        return new DruidDataSourceWrapper();
    }

}
