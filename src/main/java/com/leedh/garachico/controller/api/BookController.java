package com.leedh.garachico.controller.api;

import com.leedh.garachico.dto.BookDTO;
import com.leedh.garachico.service.feign.KakaoBookApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 설명:
 * Project: garachico
 * CLASS: BookController
 * User: 이동훈
 * Date: 2018-07-07
 */
@Slf4j
@RestController
@RequestMapping("/book/v1/*")
public class BookController {

    @Autowired
    KakaoBookApiService kakaoBookApiService;

    /**
     * 책 검색
     * @return
     */
    @PostMapping("/find")
    public BookDTO.Res getBookInfo(HttpServletRequest request,
                                   HttpServletResponse response,
                                   Model model,
                                   @ModelAttribute BookDTO.Req param ){
        log.info("getBookInfo : {} ", param);

        String query        = param.getQuery();
        String sort         = param.getSort();
        String category     = param.getCategory();
        String target       = param.getTarget();
        Integer pageNo      = param.getPageNo();
        Integer pageSize    = param.getPageSize();

        BookDTO.Res result = kakaoBookApiService.findBook(query, sort, category, target, pageNo, pageSize );

        log.info("result = {}", result);

        return result;
    }

    /**
     * 책검색 2
     * dto를 사용하지 않고 해당 데이터 가져옴
     */
    @PostMapping("/find2")
    public BookDTO.Res getBookInfo(HttpServletRequest request,
                                   HttpServletResponse response,
                                   Model model,
                                   @RequestParam("query")                                                      String query,       /*검색을 원하는 질의어*/
                                   @RequestParam(value = "sort"    , required = false)                         String sort,        /*결과 문서 정렬 방식*/
                                   @RequestParam(value = "category", required = false)                         String category,    /*검색 필드 제한*/
                                   @RequestParam(value = "target"  , required = false)                         String target,      /*카테고리 필터링*/
                                   @RequestParam(value = "page"    , required = false, defaultValue = "1")     Integer pageNo,     /*결과 페이지 번호*/
                                   @RequestParam(value = "size"    , required = false, defaultValue = "10")    Integer pageSize    /*한 페이지에 보여질 문서의 개수*/ ){



        BookDTO.Res result = kakaoBookApiService.findBook(query, sort, category, target, pageNo, pageSize );

        log.info("result = {}", result);

        return result;
    }

}
