package com.leedh.garachico.dto;

import com.leedh.garachico.entity.model.Member;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

/**
 * 설명: 로그인에 사용하는 DTO를 올림
 * Project: garachico
 * CLASS: LoginUserDetails
 * User: 이동훈
 * Date: 2018-07-12
 */
public class LoginUserDetails extends User {

    private static final String ROLE_PREFIX = "ROLE_";
    private static final long serialVersionUID = 1L;

    @Getter
    private Member member;

    public LoginUserDetails(Member member)
    {
        super( member.getUsername(), member.getPassword(), member.getRoles());
        this.member = member;
    }

}
