package com.leedh.garachico.dto;

import com.leedh.garachico.entity.Member;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

/**
 * 설명:
 * Project: garachico
 * CLASS: LoginUserDetails
 * User: 이동훈
 * Date: 2018-07-12
 */
public class LoginUserDetails extends User {

    @Getter
    private Long memberNo;

    public LoginUserDetails(Member member)
    {
        super( member.getUsername(), member.getPassword(), member.getMemberRoles());
        this.memberNo = member.getMemberNo();
    }
}
