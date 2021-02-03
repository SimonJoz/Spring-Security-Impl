package com.example.springsecurity_basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@Profile("jdbcSecurity")
public class JdbcSecurityConfig extends WebSecurityConfigurerAdapter {

    //         JDBC DETAILS SERVICE IMPLEMENTATION

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
    //         AUTHORIZATION BASE ON AUTHORITY

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers(GET, "api/templates").hasAuthority("READ")
                .anyRequest().hasAuthority("WRITE")
                .and()
                .httpBasic();
    }

    //        BASIC AUTHORIZATION WHERE ALL REQUESTS MUST BE AUTHENTICATED

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//    }

}




