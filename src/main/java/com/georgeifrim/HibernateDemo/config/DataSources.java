//package com.georgeifrim.HibernateDemo.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSources {
//
//
//    @Bean(name = "datasourcesecurity")
//    @ConfigurationProperties(prefix = "spring.datasource.datasourcesecurity")
//    public DataSource dataSourceSecurity(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "dataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//}
