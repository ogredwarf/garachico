package com.leedh.garachico.utils;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

/**
 * 설명:
 * Project: garachico
 * CLASS: ValidUtils
 * User: 이동훈
 * Date: 2018-07-13
 */
@UtilityClass
public class ValidUtils {

    /**
     * ISBN 검증
     * ISBN10 or ISBN13 둘다 검증
     * @param isbn
     * @return
     */
    public Boolean vaildISBN( final String isbn ){

        String regex = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|" +
                "(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";

        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(isbn).matches();
    }
}
