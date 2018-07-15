package com.leedh.garachico.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

/**
 * 설명: 검색 조회 책검색 기능 구현
 * Project: garachico
 * CLASS: Category
 * User: 이동훈
 * Date: 2018-07-07
 */
@Getter
@AllArgsConstructor
public enum Category {

    전체(StringUtils.EMPTY, "전체"),
    국내도서_소설 ("1", "국내도서_소설"),
    국내도서_시("3", "국내도서_시"),
    국내도서_인문("5", "국내도서_인문"),
    국내도서_요리("8", "국내도서_요리"),
    국내도서_취미스포츠("11", "국내도서_취미/스포츠"),
    국내도서_경제경영("13", "국내도서_경제/경영"),
    국내도서_자기계발("15", "국내도서_자기계발"),
    국내도서_국내도서_정치사회("17", "국내도서_정치/사회"),
    국내도서_역사문화("18", "국내도서_역사/문화"),
    국내도서_종교("21", "국내도서_종교"),
    국내도서_예술대중문화("23", "국내도서_예술/대중문화"),
    국내도서_중고등학습("25", "국내도서_중/고등학습"),
    국내도서_기술공학("26", "국내도서_기술/공학"),
    국내도서_외국어("27", "국내도서_외국어"),
    국내도서_과학("29", "국내도서_과학"),
    국내도서_취업수험서("31", "국내도서_취업/수험서"),
    국내도서_여행기행("32", "국내도서_여행/기행"),
    국내도서_컴퓨터IT("33", "국내도서_컴퓨터/IT")
    /* ... 후략 ... 너무 종류가 많음.......*/
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
