package com.leedh.garachico.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 설명:
 * Project: garachico
 * CLASS: Target
 * User: 이동훈
 * Date: 2018-07-07
 */
@Getter
@AllArgsConstructor
public enum Target {
    ALL("all", "전체"),            // 전체
    TITLE("title", "제목"),          // 제목에서 검색
    ISBN("isbn", "ISBN"),           // ISBN에서 검색
    KEYWORD("keyword", "주제어"),        // 주제어에서 검색
    CONTENTS("contents", "목차"),       // 목차에서 검색
    OVERVIEW("overview", "책소개"),       // 책소개에서 검색
    PUBLISHER("publisher", "출판사")       // 출판사에서 검색
    ;

    private final String code;  /*코드*/
    private final String desc;  /*설명*/

    public final String getDescByCode( final String code ){
        return Arrays.stream(Category.values())
                .filter( x -> x.getCode().equals(code))
                .findFirst()
                .orElse(Category.전체) /*default value*/
                .getDesc()
                ;
    }
}
