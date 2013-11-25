package com.dv.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;

@Configuration
@ComponentScan("com.dv.game")
@EnableWebMvc
@EnableTransactionManagement
public class ApplicationConfig {

    @Bean(name = "persistenceUnit")
    EntityManagerFactory entityManagerFactory() throws NamingException {

        Context context = new InitialContext();
        return (EntityManagerFactory) context.lookup("java:comp/env/persistence/persistenceUnit");
    }

    @Bean
    PlatformTransactionManager transactionManager() {

        return new JtaTransactionManager();
    }
}
