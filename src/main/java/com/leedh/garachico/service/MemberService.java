package com.leedh.garachico.service;

import com.leedh.garachico.entity.model.Member;
import com.leedh.garachico.entity.repository.MemberRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 설명:
 * Project: garachico
 * CLASS: MemberService
 * User: 이동훈
 * Date: 2018-07-11
 */
@Slf4j
@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    /* 사용자 계정 조회 */
    public Member getMember( @NonNull String username ){
        log.info("Log in request");
        return memberRepository.findOneByUsername(username);
    }

    /*회원 가입*/
    @Transactional
    public String joinMember(@NonNull final String username,
                             @NonNull final String password ){


        if( null != memberRepository.findOneByUsername(username)){
            return "이미 존재하는 계정입니다.";
        }

        /* DB 저장 */
        memberRepository.save(Member.builder()
                .username(username)
                .pswd( new BCryptPasswordEncoder().encode(password))
                .role("USER")   /*기본값*/
                .build()
        );

        return StringUtils.EMPTY;

    }

}
