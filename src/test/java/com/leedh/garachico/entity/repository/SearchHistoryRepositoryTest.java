package com.leedh.garachico.entity.repository;

import com.leedh.garachico.entity.model.Member;
import com.leedh.garachico.entity.model.SearchHistory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 설명:
 * Project: garachico
 * CLASS: SearchHistoryRepositoryTest
 * User: 이동훈
 * Date: 2018-07-13
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SearchHistoryRepositoryTest {

    @Autowired
    SearchHistoryRepository searchHistoryRepository;

    @Autowired
    MemberRepository memberRepository;

    @Before
    public void before() {
        // 사용자 계정 정보 추가
        memberRepository.save(Member.builder()
                .username("testUser")
                .pswd( new BCryptPasswordEncoder().encode("testUserPassword"))
                .role("USER")   /*기본값*/
                .build());
    }


    @Test
    public void testSaveBookMark() throws Exception{
        // 사용자 계정 정보 추가
        Member member = memberRepository.findOneByUsername("testUser");
        assertNotNull( member );

        /* Stream 을 이용한 방식 */
        IntStream.range(0, 15 ).forEach( x ->{
            searchHistoryRepository.save( SearchHistory.builder()
                    .member(member)
                    .query("java_" + x )
                    .sort( 0==x%2? "accuracy" : "recency" )
                    .build()
            );
        });

        /* for문을 이용한 방식 */
        /*for( int i = 0; i < 15 ; i++ ){
            searchHistoryRepository.save( SearchHistory.builder()
                    .member(member)
                    .query("java_" + i )
                    .sort( 0==i%2? "accuracy" : "recency" )
                    .build()
            );

        }*/


        Sort sort = new Sort( Sort.Direction.DESC, "createDt");
        /*최근 10건의 데이터만 조회*/
        List<SearchHistory> last10SrchHistory = searchHistoryRepository.findFirst10ByMember( member, sort );

        assertNotNull(last10SrchHistory);
        assertEquals(10, last10SrchHistory.size());


    }

    /* 테스트 이후 삭제 */
    @After
    public void after(){

        searchHistoryRepository.deleteAll();
        memberRepository.deleteByUsername( "testUser");
    }
}