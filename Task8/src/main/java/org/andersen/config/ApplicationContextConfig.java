package org.andersen.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "org.andersen")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class ApplicationContextConfig {
    private final Environment env;

    public ApplicationContextConfig(Environment env) {
        this.env = env;
    }

    @Bean
    @ConditionalOnProperty(name = "app.conditionalBean.available", havingValue = "true")
    public ThisIsMyFirstConditionalBean thisIsMyFirstConditionalBean() {
        return new ThisIsMyFirstConditionalBean();
    }
}
