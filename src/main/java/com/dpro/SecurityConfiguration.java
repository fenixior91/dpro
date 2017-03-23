package com.dpro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("student").password("student").roles("STUDENT");
        auth.inMemoryAuthentication().withUser("instructor").password("instructor").roles("INSTRUCTOR");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/instructor/**").access("hasRole('INSTRUCTOR')")
                .antMatchers("/student/**").access("hasRole('STUDENT')")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/login_success").failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }
}
