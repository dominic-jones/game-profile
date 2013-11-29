package com.dv.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.dv.game")
@EnableWebMvc
@EnableTransactionManagement
public class ApplicationConfig {

    @Bean
    LocalValidatorFactoryBean localValidatorFactoryBean() {

        return new LocalValidatorFactoryBean();
    }
}
