package com.leedh.garachico.service;

import com.leedh.garachico.dto.LoginUserDetails;
import com.leedh.garachico.entity.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 설명:
 * Project: garachico
 * CLASS: LoginUserDetailsService
 * User: 이동훈
 * Date: 2018-07-12
 */
@Service
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 계정 요청
        Member member = memberService.getMember(username);

        if(member == null ){
            throw new UsernameNotFoundException("계정이 없습니다. :"+ username);
        }

        return new LoginUserDetails(member);
    }
}
