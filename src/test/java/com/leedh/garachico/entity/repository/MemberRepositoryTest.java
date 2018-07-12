package com.leedh.garachico.entity.repository;

import com.leedh.garachico.entity.model.Member;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * project: garachico
 * User: 이동훈
 * Date: 2018-07-07
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @After
    public void cleanup() throws Exception {
        /**
         이후 테스트 코드에 영향을 끼치지 않기 위해
         테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
         **/

        memberRepository.deleteAll();
    }

    @Test
    public void testMemberRepository() throws Exception {

        BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();

        final String userId = "wishadow@gmail.com";
        final String passwd = cryptPasswordEncoder.encode("password1!");
        memberRepository.save(Member.builder()
                .username(userId)
                .pswd(passwd)
                .role("USER")
                .build());

        List<Member> memberList = memberRepository.findAll();

        Member member = memberList.get(0);
        assertThat(member.getUsername(), is(userId));
        assertThat(member.getPassword(), is(passwd));
    }
}