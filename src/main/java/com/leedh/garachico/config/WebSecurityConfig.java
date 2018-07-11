package com.leedh.garachico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * 설명:
 * Project: garachico
 * CLASS: WebSecurityConfig
 * User: 이동훈
 * Date: 2018-07-11
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Security configuration for H2 console access
        // !!!! You MUST NOT use this configuration for PRODUCTION site !!!!
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/**", "/resources/**", "/webjars/**").permitAll();

        http
                .authorizeRequests()
                /*.antMatchers("/", "/").permitAll()*/
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")    // 권한없는 url이 접근하면 자동으로 지정된 페이지로 이동
                .defaultSuccessUrl("/", true)   // 로그인 하고 지정한 url이 없는 경우 default 페이지로 이동
                .failureForwardUrl("/login?fail=true")  // 로그인 실패시 이동되는 url 설정
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/login")
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
