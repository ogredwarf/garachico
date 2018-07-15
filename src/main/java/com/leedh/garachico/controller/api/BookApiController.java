package com.leedh.garachico.controller.api;

import com.leedh.garachico.dto.LoginUserDetails;
import com.leedh.garachico.entity.model.Member;
import com.leedh.garachico.entity.model.SearchHistory;
import com.leedh.garachico.service.BookMarkService;
import com.leedh.garachico.service.SearchHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 설명:
 * Project: garachico
 * CLASS: BookApiController
 * User: 이동훈
 * Date: 2018-07-14
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class BookApiController {

    @Autowired
    SearchHistoryService searchHistoryService;

    @Autowired
    BookMarkService bookMarkService;

    /**
     * 최신 검색 리스트 조회
     * @return
     */
    @PostMapping("history/list")
    public HashMap<String, Object> getHistory(){

        log.info("history/list");

        HashMap<String, Object> result = new HashMap<>();
        // 로그인 사용자 조회
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member loginedMember = ((LoginUserDetails) auth.getPrincipal()).getMember();
        // 사용자 검색 기록 저장
        List<SearchHistory> historyList = searchHistoryService.getLast10SearchHistoryList(loginedMember);

        log.info("historyList = {}", historyList);

        result.put("status", true);
        result.put("historyList", historyList);

        return result;
    }

    /**
     * 북마크 추가
     */
    @PostMapping("/bookmark/add")
    public HashMap<String, Object> addBookMark(Model model,
                              @RequestParam("isbn") final String    isbn,
                              @RequestParam("title") final String   title,
                              @RequestParam("url") final String     url
                              ) {

        HashMap<String, Object> result = new HashMap<>();
        Boolean isSuccess = false;

        // 로그인 사용자 조회
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member loginedMember = ((LoginUserDetails) auth.getPrincipal()).getMember();

        isSuccess = bookMarkService.add( isbn, title, url, loginedMember );
        result.put("isSuccess", isSuccess);

        if(isSuccess){
            result.put("message", "북마크 추가에 성공하였습니다.");
        } else {
            result.put("message", "이미 등록된 북마크 입니다.");
        }

        return result;
    }

    /**
     * 북마크 삭제
     */
    @PostMapping("/bookmark/del")
    public HashMap<String, Object> removeBookMark(Model model,
                                               @RequestParam("isbn") final String    isbn,
                                               @RequestParam("title") final String   title,
                                               @RequestParam("url") final String     url
    ) {

        HashMap<String, Object> result = new HashMap<>();
        Boolean isSuccess = false;

        // 로그인 사용자 조회
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member loginedMember = ((LoginUserDetails) auth.getPrincipal()).getMember();

        isSuccess = bookMarkService.remove( isbn, title, url, loginedMember );
        result.put("isSuccess", isSuccess);

        if(isSuccess){
            result.put("message", "북마크 제거에 성공하였습니다.");
        } else {
            result.put("message", "해당하는 북마크 정보가 없습니다.");
        }

        return result;
    }

}
