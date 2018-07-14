package com.leedh.garachico.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 설명: 리스트에서 팝업을 위해 사용
 * Project: garachico
 * CLASS: BookDetailDTO
 * User: 이동훈
 * Date: 2018-07-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDetailDTO {
    String title;           //title	도서 제목	String
    String contents;        //contents	도서 소개	String
    String url;             //url	책 링크 (URL)	String
    String isbn;            //isbn	ISBN 번호. 국제 표준 도서번호(ISBN10,ISBN13) (ISBN10,ISBN13 중에 하나 이상 존재하면 ' '(공백)을 구분자로 출렴됨)	String
    String datetime;        //datetime	도서 출판날짜. ISO 8601. [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]	String
    List<String> authors;       //authors	도서 저자 리스트	Array of String
    String publisher;       //publisher	출판사	String
    List<String> translators;   //translators	번역자 리스트	Array of String
    Integer price;          //price	도서 정가	Integer
    Integer salePrice;      //sale_price	도서 판매가	Integer
    String saleYn;          //sale_yn	도서 판매 여부	Y or N
    String category;        //category	도서 카테고리 정보	String
    String thumbnail;       //thumbnail	도서 표지 썸네일	String
    String barcode;         //barcode	교보문고 바코드 정보	String
    String ebookBarcode;    //ebook_barcode	교보문고 전자책 바코드 정보	String
    String status;          //status	도서 상태 정보(정상, 품절, 절판 등). 변경 가능성이 있으니 노출용으로만 사용 권장.	String
}
