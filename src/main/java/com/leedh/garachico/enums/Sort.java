package com.leedh.garachico.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 설명:
 * Project: garachico
 * CLASS: Sort
 * User: 이동훈
 * Date: 2018-07-07
 */
@Getter
@AllArgsConstructor
public enum Sort {
    ACCURACY("accuracy", "정확도"),
    RECENCY ("recency", "최신순");

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
