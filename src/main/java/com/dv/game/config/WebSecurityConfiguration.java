package com.dv.game.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.inject.Inject;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration
        extends WebSecurityConfigurerAdapter {

    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                AuthenticationProvider authenticationProvider) throws Exception {

        auth.authenticationProvider(authenticationProvider);
    }

    /*
     * All requests to the resources folder (css etc) should be ignored by the filters. (This will
     * still pass secure through https).
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //All users may register, only admins may admin
        //All other requests must be authenticated
        http
                .authorizeRequests()
                .antMatchers("/test/register").permitAll()
                .antMatchers("/test/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        //Define the login form, accessible by all
        http
                .formLogin()
                .defaultSuccessUrl("/test/profile")
                .loginPage("/test/login")
                .permitAll()

// Map http ports to https
                .and()
                .portMapper()
                .http(9090).mapsTo(9191)

//And ensure all requests use the https channel
                .and()
                .requiresChannel()
                .anyRequest()
                .requiresSecure();
    }
}
