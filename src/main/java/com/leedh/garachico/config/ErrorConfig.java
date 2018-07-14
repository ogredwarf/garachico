package com.leedh.garachico.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;

/**
 * 설명: 에러 페이지 설정
 * - spring boot 2.0 부터 에러 페이지 설정이 변경 되었다.
 * Project: garachico
 * CLASS: ErrorConfig
 * User: 이동훈
 * Date: 2018-07-14
 */
@Configuration
public class ErrorConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        registry.addErrorPages(new ErrorPage("/error"));
    }
}
