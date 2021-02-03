package com.example.springsecuritycustom.config;

import com.example.springsecuritycustom.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;

import static org.springframework.http.HttpMethod.GET;

@Profile("form")
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HttpFormSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("index", "/css/*", "/login", "/login?logout=success", "/login?logout=error").permitAll()
                .mvcMatchers(GET, "/api/**", "/templates").authenticated()
                .anyRequest().hasRole("ADMIN")
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/templates")
                    .usernameParameter("username") // default
                    .passwordParameter("password") // default
                    .and()
                .rememberMe() // default remember me time is 2 weeks
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(5))
                    .key("somethingVerySecureInHere")
                    .rememberMeParameter("remember-me") // default
                .and()
                .logout()
                    .logoutUrl("/logout") // default
                    .logoutSuccessUrl("/login?logout=success")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me");


    }
}
