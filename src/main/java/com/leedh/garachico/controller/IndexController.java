package com.leedh.garachico.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 기본 페이지 라우팅
 * project: garachico
 * User: 이동훈
 * Date: 2018-07-07
 */

@Slf4j
@Controller
public class IndexController {

    /**
     * 기본 페이지 설정
     */
    @GetMapping("/")
    public String index() {
        log.debug("index");
        return "index";
    }
}
