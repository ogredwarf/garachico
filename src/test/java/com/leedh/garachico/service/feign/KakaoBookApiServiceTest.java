package com.leedh.garachico.service.feign;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotEquals;

/**
 * 설명:
 * Project: garachico
 * CLASS: KakaoBookApiServiceTest
 * User: 이동훈
 * Date: 2018-07-08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class KakaoBookApiServiceTest {

    @Autowired
    KakaoBookApiService kakaoBookApiService;

    @Test
    public void findBook(){

        String result = kakaoBookApiService.findBookRaw("자바");
        assertNotEquals(StringUtils.defaultString(result).length(), 0);
    }

}