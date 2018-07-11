package com.leedh.garachico.service;

import com.leedh.garachico.entity.Member;
import com.leedh.garachico.entity.MemberRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return memberRepository.findOneByUsername(username);
    }

    /*회원 가입*/
    public String joinMember(@NonNull final String username,
                             @NonNull final String password ){


        if( null != memberRepository.findOneByUsername(username)){
            return "이미 존재하는 계정입니다.";
        }

        /* 파일 저장 */
        memberRepository.save(Member.builder()
                .username(username)
                .pswd(password)
                .role("USER")
                .build()
        );

        return StringUtils.EMPTY;

    }

}
