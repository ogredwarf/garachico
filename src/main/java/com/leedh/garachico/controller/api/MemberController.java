package com.leedh.garachico.controller.api;

import com.leedh.garachico.service.MemberService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 설명: 회원 가입 및 관리
 * Project: garachico
 * CLASS: MemberController
 * User: 이동훈
 * Date: 2018-07-12
 */
@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    /*member/login_process*/


/*    @RequestMapping("/join_process")
    String join( Model model,
                 @RequestParam("username") @NonNull final String username,
                 @RequestParam("password") @NonNull final String password
    ){
        Boolean status = false;
        String message = StringUtils.EMPTY;

        log.debug("join user : {} {}", username, password);

        if(StringUtils.isBlank(username)){
            message = "username 를 다시 입력 바랍니다.";
        }
        else if(StringUtils.isBlank(password)){
            message = "password 를 다시 입력 바랍니다.";
        }
        else {

            message = memberService.joinMember( username, password);
            if( StringUtils.isBlank(message)) status = true;
        }

        log.debug("result : {} {}", status, message);

        model.addAttribute("status", status);
        model.addAttribute("error_message", message);

        return status? "redirect:/login" : "redirect:/join";
    }*/

    @PostMapping("/join_process")
    public HashMap<String, Object> join(Model model,
                                           @RequestParam("username") @NonNull final String username,
                                           @RequestParam("password") @NonNull final String password
    ){
        Boolean status = false;
        HashMap<String, Object> result = new HashMap<>();
        String message = StringUtils.EMPTY;

        log.debug("join user : {} {}", username, password);

        if(StringUtils.isBlank(username)){
            message = "username 를 다시 입력 바랍니다.";
        }
        else if(StringUtils.isBlank(password)){
            message = "password 를 다시 입력 바랍니다.";
        }
        else {

            message = memberService.joinMember( username, password);
            if( StringUtils.isBlank(message)) status = true;
        }

        log.debug("result : {} {}", status, message);

        result.put("status", status);
        result.put("returnUrl", status? "/login" : "/join" );
        result.put("error_message", message);

        return result;
    }

}
