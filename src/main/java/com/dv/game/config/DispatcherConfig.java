package com.dv.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;

import static org.hibernate.engine.transaction.jta.platform.internal.SunOneJtaPlatform.TM_NAME;

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
    PlatformTransactionManager transactionManager() throws NamingException {

        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();

        //The JtaTransactionManager does its own job checking common locations for various app servers. This causes an error on startup
        //as it checks invalid locations, so specifying it here.
        jtaTransactionManager.setTransactionManagerName(TM_NAME);

        return jtaTransactionManager;
    }
}
