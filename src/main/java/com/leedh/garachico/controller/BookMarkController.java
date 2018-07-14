package com.leedh.garachico.controller;

import com.leedh.garachico.dto.LoginUserDetails;
import com.leedh.garachico.entity.model.BookMark;
import com.leedh.garachico.entity.model.Member;
import com.leedh.garachico.service.BookMarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 설명: 북마크 추가 삭제 관련
 * Project: garachico
 * CLASS: BookMarkController
 * User: 이동훈
 * Date: 2018-07-13
 */
@Controller
@Slf4j
@RequestMapping("/bookmark/")
public class BookMarkController {

    @Autowired
    BookMarkService bookMarkService;

    /**
     * 책 검색 결과 조회
     * @param model
     * @return
     */
    @RequestMapping("list")
    public String list( Model model){

        /* 로그인한 사용자 정보 가져오기*/
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member loginedMember = ((LoginUserDetails) auth.getPrincipal()).getMember();

        List<BookMark> list = bookMarkService.list(loginedMember);

        model.addAttribute("bookmarkList", list);
        return "/bookmark/list";
    }

}
