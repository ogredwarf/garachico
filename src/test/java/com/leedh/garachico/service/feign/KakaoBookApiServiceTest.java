package com.leedh.garachico.service.feign;

import com.leedh.garachico.dto.BookDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * 설명:
 * Project: garachico
 * CLASS: KakaoBookApiServiceTest
 * User: 이동훈
 * Date: 2018-07-08
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class KakaoBookApiServiceTest {

    @Autowired
    KakaoBookApiService kakaoBookApiService;

    @Test
    public void findBook(){

        BookDTO.Res result = kakaoBookApiService.findBook("자바");
        assertNotNull(result);
        assertTrue(result.getMeta().getTotalCount()>0);
    }

}