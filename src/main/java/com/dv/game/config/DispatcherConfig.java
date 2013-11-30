package com.dv.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    /*
     * Basic view configuration.
     */
    @Bean
    InternalResourceViewResolver internalResourceViewResolver() {

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /*
     * Provides access to the PersistenceUnit from the JNDI as a bean.
     */
    @Bean(name = "persistenceUnit")
    EntityManagerFactory entityManagerFactory() throws NamingException {

        Context context = new InitialContext();
        return (EntityManagerFactory) context.lookup("java:comp/env/persistence/persistenceUnit");
    }

    /*
     * The JtaTransactionManager does its own job checking common locations for various app servers. However, this
     * causes non-fatal errors on startup as it checks invalid locations.
     */
    @Bean
    PlatformTransactionManager transactionManager() throws NamingException {

        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManagerName(TM_NAME);
        return jtaTransactionManager;
    }

    /*
     * JSR 303 Bean Validator
     */
    @Bean
    LocalValidatorFactoryBean localValidatorFactoryBean() {

        return new LocalValidatorFactoryBean();
    }

    /*
     * Provides access to a PasswordEncoder, so we don't store passwords as plain text..
     */
    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    /*
     * Configures the Spring AuthenticationProvider with the details it needs to authenticate our users.
     */
    @Bean
    AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder,
                                                  UserDetailsService userDetailsService) {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }
}
