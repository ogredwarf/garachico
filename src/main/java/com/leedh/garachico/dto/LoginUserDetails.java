package com.leedh.garachico.dto;

import com.leedh.garachico.entity.model.Member;
import com.leedh.garachico.entity.model.MemberRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 설명:
 * Project: garachico
 * CLASS: LoginUserDetails
 * User: 이동훈
 * Date: 2018-07-12
 */
public class LoginUserDetails extends User {

    private static final String ROLE_PREFIX = "ROLE_";
    private static final long serialVersionUID = 1L;

    @Getter
    private Long memberNo;

    public LoginUserDetails(Member member)
    {
        super( member.getUsername(), member.getPassword(), makeGrantedAuthority(member.getRoles()));
        this.memberNo = member.getMemberNo();
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles){
        List<GrantedAuthority> list = new ArrayList<>();
        roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
        return list;
    }
}
