package com.leedh.garachico.controller;

import com.leedh.garachico.dto.BookDTO;
import com.leedh.garachico.dto.BookDetailDTO;
import com.leedh.garachico.dto.LoginUserDetails;
import com.leedh.garachico.entity.model.Member;
import com.leedh.garachico.service.SearchHistoryService;
import com.leedh.garachico.service.feign.KakaoBookApiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.IntStream;

/**
 * 설명:
 * Project: garachico
 * CLASS: BookController
 * User: 이동훈
 * Date: 2018-07-09
 */

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    KakaoBookApiService kakaoBookApiService;

    @Autowired
    SearchHistoryService searchHistoryService;

    /**
     * 책 검색 방안
     */
    @RequestMapping("find")
    public String find(HttpServletRequest request,
                       HttpServletResponse response,
                       ModelMap model,
                       @RequestParam("query")                                                      String query,        /*검색을 원하는 질의어*/
                       @RequestParam(value = "sort")                                               String sort,         /*결과 문서 정렬 방식*/
                       @RequestParam(value = "category", required = false)                         String category,     /*검색 필드 제한*/
                       @RequestParam(value = "target"  , required = false)                         String target,       /*카테고리 필터링*/
                       @RequestParam(value = "page"    , required = false, defaultValue = "1")     Integer pageNo,      /*결과 페이지 번호*/
                       @RequestParam(value = "size"    , required = false, defaultValue = "10")    Integer pageSize     /*한 페이지에 보여질 문서의 개수*/
    ){
        Boolean status = false;
        log.debug(StringUtils.repeat("-", 30).concat("\n"));
        log.debug("query = {}", query);
        log.debug("sort = {}", sort);
        log.debug("category = {}", category);
        log.debug("target = {}", target);
        log.debug("page = {}", pageNo);
        log.debug("size = {}", pageSize);
        log.debug(StringUtils.repeat("-", 30).concat("\n"));

        // 카카오 API를 이용해서 책 검색
        BookDTO.Res result = kakaoBookApiService.findBook(query, sort, category, target, pageNo, pageSize );

        /**
         * 성공시 해야 할 것
         * 1. row count 적용
         * 2. 검색 기록 저장
         */
        if(result != null ) {
            // row count 집어 넣기
            IntStream.range(0, result.getDocuments().size())
                    .forEach( i ->{ result.getDocuments().get(i).setSeq((long)(((pageNo-1) * pageSize)+ i));});

            if( pageNo == 1) {
                // 로그인 사용자 조회
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                Member loginedMember = ((LoginUserDetails) auth.getPrincipal()).getMember();

                // 사용자 검색 기록 저장
                searchHistoryService.save(query, sort, category, target, loginedMember);
            }

            model.put("result", result);
            status = true;
        }

        model.put("status", status);
        model.put("page",   pageNo);

        return "/book/list";
    }


    /**
     * 상세 페이지
     * - DTO로 해당 정보를 가져옴
     */
    @RequestMapping("detail")
    public String getDetail(HttpServletRequest request,
                       HttpServletResponse response,
                       ModelMap model,
                       @ModelAttribute BookDetailDTO bookDetail       /*검색을 원하는 질의어*/
    ){

        log.debug("param = {}", bookDetail);
        model.put("result", bookDetail);
        return "/book/detail";
    }

    /**
     * DTO 방식
     * 실제로는 사용되고 있지 않음
     * - 이런 방식으로 구현이 가능하다.
     */
    @PostMapping("findAllByDTO")
    public String find(HttpServletRequest request,
                       HttpServletResponse response,
                       ModelMap model,
                       @ModelAttribute BookDTO.Req param
    ){
        Boolean status = false;
        log.debug( "book/getList : param = {}", param );

        String query        = param.getQuery();
        String sort         = param.getSort();
        String category     = param.getCategory();
        String target       = param.getTarget();
        Integer pageNo      = param.getPageNo();
        Integer pageSize    = param.getPageSize();

        BookDTO.Res result = kakaoBookApiService.findBook(query, sort, category, target, pageNo, pageSize );

        if(result != null ) {
            model.put("result", result);
            status = true;
        }

        model.put("status", status);
        model.put("page",   pageNo);
        model.put("pageSize",   pageSize);

        return "book/list";
    }
}
