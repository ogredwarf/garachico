package com.leedh.garachico.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Autowired
    UserDetailsService myDetailsService;

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
                .antMatchers("/join", "/member/join_process").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")                            // 권한없는 url이 접근하면 자동으로 지정된 페이지로 이동
                .loginProcessingUrl("/login_process")    // 로그인 진행 url
                .defaultSuccessUrl("/", true)   // 로그인 하고 지정한 url이 없는 경우 default 페이지로 이동
                .failureForwardUrl("/login?fail=true")          // 로그인 실패시 이동되는 url 설정
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")            // 로그아웃 url
                .logoutSuccessUrl("/login?logout=true")
                .deleteCookies("JSESSIONID")
                .permitAll()
                ;

        /**
         * 중복 로그인 방지
         */
        http.sessionManagement()
                .invalidSessionUrl("/login")
                .sessionAuthenticationErrorUrl("/login")
                .maximumSessions(1)
                .expiredUrl("/login")
                .maxSessionsPreventsLogin(true)
                ;

    }

    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    protected void configure(AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService(myDetailsService)
                .passwordEncoder( passwordEncoder() );
    }


}
