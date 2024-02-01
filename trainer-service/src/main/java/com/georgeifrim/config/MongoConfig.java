package com.georgeifrim.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MongoConfig {

    @Autowired
    MongoDatabaseFactory dbFactory;

    @Bean
    public MongoTransactionManager transactionManager(){
        return new MongoTransactionManager(dbFactory);
    }
}
