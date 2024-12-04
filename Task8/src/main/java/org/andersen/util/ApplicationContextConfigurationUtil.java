package org.andersen.util;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "org.andersen")
public class ApplicationContextConfigurationUtil {
    @Bean
    @Scope("singleton")
    public DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser("postgres");
        dataSource.setPassword("pass123");
        dataSource.setURL("jdbc:postgresql://localhost:5432/my_ticket_service_db");
        return dataSource;
    }
}
