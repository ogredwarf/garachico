package com.leedh.garachico.controller.api;

import com.leedh.garachico.dto.LoginUserDetails;
import com.leedh.garachico.entity.model.Member;
import com.leedh.garachico.entity.model.SearchHistory;
import com.leedh.garachico.service.SearchHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /**
     * 최신 검색 리스트 조회
     * @return
     */
    @PostMapping("getHistory")
    public HashMap<String, Object> getHistory(){

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

}
