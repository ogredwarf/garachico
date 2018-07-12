package com.leedh.garachico.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 설명: BookDTO
 * Project: garachico
 * CLASS: BookDTO
 * User: 이동훈
 * Date: 2018-07-08
 */
public class BookDTO {

    /**
     * 해당 Req는 DTO를 이용해서도 받을 수 있다는 것을 보여주기 위해 만듬
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Req {

        private String query;       /*검색어*/
        private String sort;        /*정렬방식*/
        private String category;    /*분류*/
        private String target;
        private Integer pageNo;
        private Integer pageSize;

    }

    /**
     * 실제 KAKAOAPI 책 검색 결과를 DTO에 저장
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Res {

        @SerializedName("meta")
        private Meta meta;
        @SerializedName("documents")
        private List<Document> documents;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public static class Meta {

            /*@SerializedName("total_count")*/
            Integer totalCount; //검색어에 검색된 문서수
            /*@SerializedName("pageable_count")*/
            Integer pageableCount; // total_count 중에 노출가능 문서수
            /*@SerializedName("is_end")*/
            Boolean isEnd;  // 현재 페이지가 마지막 페이지인지 여부. 값이 false이면 page를 증가시켜 다음 페이지를 요청할 수 있음.
        }


        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public static class Document {
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
    }
}
