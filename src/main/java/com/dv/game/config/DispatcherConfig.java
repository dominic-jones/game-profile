package com.dv.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;

@Configuration
public class DispatcherConfig {

    @Bean
    InternalResourceViewResolver internalResourceViewResolver() {

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

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
