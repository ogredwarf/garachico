package com.leedh.garachico.entity.repository;

import com.leedh.garachico.entity.model.BookMark;
import com.leedh.garachico.entity.model.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 설명:
 * Project: garachico
 * CLASS: BookMarkRepositoryTest
 * User: 이동훈
 * Date: 2018-07-13
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookMarkRepositoryTest {

    @Autowired
    BookMarkRepository bookMarkRepository;

    @Autowired
    MemberRepository memberRepository;

    @Before
    public void before() {
        // 사용자 계정 정보 추가
        memberRepository.save(Member.builder()
                .username("testUser")
                .pswd(new BCryptPasswordEncoder().encode("testUserPassword"))
                .role("USER")   /*기본값*/
                .build());
    }


    @Test
    public void testSaveBookMark() {
        // 사용자 계정 정보 추가

        Member member = memberRepository.findOneByUsername("testUser");

        assertNotNull(member);

        IntStream.range(0, 10).forEach(x -> {
            bookMarkRepository.save(BookMark.builder()
                    .isbn("isbn_" + x)
                    .title("title_" + x)
                    .url("https://www.kakaobank.com/")
                    .member(member)
                    .build());
        });

        List<BookMark> listBookMark = bookMarkRepository.findAllByMember(member);

        assertNotNull(listBookMark);
        assertNotEquals(0, listBookMark.size());

    }

    /* 테스트 이후 삭제 */
    @After
    public void after() {

        bookMarkRepository.deleteAll();
        memberRepository.deleteByUsername("testUser");
    }

}