package com.dpro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.dpro.utils.DatabaseColumns.*;
import static com.dpro.utils.DatabaseTables.*;

/**
 * Konfiguracja Spring Security
 *
 * @author Tomasz Truszkowski
 */
@Configuration
@EnableWebSecurity
@Import(WebAppConfiguration.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DriverManagerDataSource dataSource;

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    private static final String SELECT_USER_QERY
            = "SELECT " + USER_NAME_COLUMN + ", " + USER_PASSWORD_COLUMN + ", " + USER_ENABLED_COLUMN + "\n"
            + "FROM " + USERS_TABLE + "\n"
            + "WHERE " + USER_NAME_COLUMN + "= ?";

    private static final String SELECT_ROLE_QUERY
            = "SELECT " + USER_NAME_COLUMN + ", " + ROLES_ROLE_COLUMN + "\n"
            + "FROM " + ROLES_TABLE + "\n"
            + "WHERE " + USER_NAME_COLUMN + " = ?";

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(SELECT_USER_QERY)
                .authoritiesByUsernameQuery(SELECT_ROLE_QUERY);

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
