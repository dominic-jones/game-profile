package com.dv.game.config;

import org.fusesource.scalate.spring.view.ScalateViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;

@Configuration
@EnableWebMvc
@ComponentScan("com.dv.game")
@EnableTransactionManagement
public class DispatcherConfig {

    @Bean
    ScalateViewResolver scalateViewResolver() {

        ScalateViewResolver resolver = new ScalateViewResolver();
        resolver.setOrder(1);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".scaml");
        return resolver;
    }

    @Bean
    InternalResourceViewResolver internalResourceViewResolver() {

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setOrder(2);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
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
