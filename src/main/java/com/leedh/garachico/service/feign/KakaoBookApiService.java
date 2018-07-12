package com.leedh.garachico.service.feign;

import com.leedh.garachico.config.KakaoApiFeignConfig;
import com.leedh.garachico.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 설명: 카카오 책 검색 KAKAO API 연결
 * - https://developers.kakao.com/docs/restapi/search#책-검색
 * Project: garachico
 * CLASS: KakaoBookApiService
 * User: 이동훈
 * Date: 2018-07-07
 */

@FeignClient(name = "grachico-kakaobook", url="${kakao-book.api.url}", configuration = KakaoApiFeignConfig.class )
@Service
public interface KakaoBookApiService {

    /*책 검색 API*/
    @RequestMapping( method = RequestMethod.GET, value = "/v2/search/book", produces={"application/json; charset=UTF-8"})
    BookDTO.Res findBook(
            @RequestParam("query")                                                      String query,       /*검색을 원하는 질의어*/
            @RequestParam(value = "sort"    , required = false)                         String sort,        /*결과 문서 정렬 방식*/
            @RequestParam(value = "category", required = false)                         String category,    /*카테고리 필터링*/
            @RequestParam(value = "target"  , required = false)                         String target,      /*검색 필드 제한*/
            @RequestParam(value = "page"    , required = false, defaultValue = "1")     Integer page,       /*결과 페이지 번호*/
            @RequestParam(value = "size"    , required = false, defaultValue = "10")    Integer size        /*한 페이지에 보여질 문서의 개수*/
    );


    @RequestMapping( method = RequestMethod.GET, value = "/v2/search/book", produces={"application/json; charset=UTF-8"})
    BookDTO.Res findBook(
            @RequestParam("query")                                                      String query       /*검색을 원하는 질의어*/
    );

    @RequestMapping( method = RequestMethod.GET, value = "/v2/search/book", produces={"application/json; charset=UTF-8"})
    BookDTO.Res findBookByIsbn(
            @RequestParam("query")                                                      String query,       /*검색을 원하는 질의어*/
            @RequestParam(value = "target"  , required = false)                         String target       /*검색 필드 제한*/
    );

    /*테스트용 json 표시 */
    @RequestMapping( method = RequestMethod.GET, value = "/v2/search/book", produces={"application/json; charset=UTF-8"})
    String findBookRaw(
            @RequestParam("query")                                                      String query       /*검색을 원하는 질의어*/
    );

}
