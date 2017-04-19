package com.dpro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Import(WebAppConfiguration.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DriverManagerDataSource dataSource;

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from user where username=?")
                .authoritiesByUsernameQuery("select username, role from roles where username=?");

        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/", "/static/**").permitAll().antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/instructor/**").hasRole("INSTRUCTOR").antMatchers("/student/**").hasRole("STUDENT").and()
                .formLogin().loginPage("/login").successHandler(customSuccessHandler).failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password").and().csrf().and().exceptionHandling()
                .accessDeniedPage("/403");
    }
}
