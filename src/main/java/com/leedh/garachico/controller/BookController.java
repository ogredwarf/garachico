package com.leedh.garachico.controller;

import com.leedh.garachico.dto.BookDTO;
import com.leedh.garachico.enums.Target;
import com.leedh.garachico.service.feign.KakaoBookApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 설명:
 * Project: garachico
 * CLASS: BookController
 * User: 이동훈
 * Date: 2018-07-09
 */

@Slf4j
@Controller
public class BookController {

    @Autowired
    KakaoBookApiService kakaoBookApiService;

    /**
     * 책 검색 방안
     */
    @RequestMapping("book/list")
    public String find(HttpServletRequest request,
                       HttpServletResponse response,
                       ModelMap model,
                       @RequestParam("query")                                                      String query,       /*검색을 원하는 질의어*/
                       @RequestParam(value = "sort"    , required = false)                         String sort,        /*결과 문서 정렬 방식*/
                       @RequestParam(value = "category", required = false)                         String category,    /*검색 필드 제한*/
                       @RequestParam(value = "target"  , required = false)                         String target,      /*카테고리 필터링*/
                       @RequestParam(value = "page"    , required = false, defaultValue = "1")     Integer pageNo,     /*결과 페이지 번호*/
                       @RequestParam(value = "size"    , required = false, defaultValue = "10")    Integer pageSize    /*한 페이지에 보여질 문서의 개수*/
    ){

        log.debug("quert = {}", query);

        BookDTO.Res result = kakaoBookApiService.findBook(query, sort, category, target, pageNo, pageSize );

        model.put("result", result);
        model.put("page", pageNo);

        return "book/list";
    }


    @GetMapping("book/detail")
    public String getDetail(HttpServletRequest request,
                       HttpServletResponse response,
                       ModelMap model,
                       @RequestParam("bookid")                                                      String bookid       /*검색을 원하는 질의어*/
    ){

        log.debug("quert = {}", bookid);

        bookid = bookid.replaceAll("[^0-9]", "");
        BookDTO.Res result = kakaoBookApiService.findBookByIsbn(bookid, Target.ISBN.getCode() );

        model.put("result", result);

        return "book/detail";
    }

    /**
     * DTO 방식
     */
    /**
     * 책 검색 방안 1
     */
    @PostMapping("book/getListByDTO")
    public String find(HttpServletRequest request,
                       HttpServletResponse response,
                       ModelMap model,
                       @ModelAttribute BookDTO.Req param
    ){

        log.debug( "book/getList : param = {}", param );

        String query        = param.getQuery();
        String sort         = param.getSort();
        String category     = param.getCategory();
        String target       = param.getTarget();
        Integer pageNo      = param.getPageNo();
        Integer pageSize    = param.getPageSize();

        BookDTO.Res result = kakaoBookApiService.findBook(query, sort, category, target, pageNo, pageSize );

        model.put("result", result);

        return "book/list";
    }
}
