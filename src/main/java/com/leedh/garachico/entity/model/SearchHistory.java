package com.leedh.garachico.entity.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 설명: 최근 검색 결과 저장
 * Project: garachico
 * CLASS: SearchHistory
 * User: 이동훈
 * Date: 2018-07-13
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "tlb_srch_hist")
@ToString
public class SearchHistory {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long srchId;

    @Column(nullable = false, unique = true)
    private String query;       /*검색어*/

    @Column(nullable = false)
    private String sort;        /*정렬방식*/

    private String category;    /*분류*/
    private String target;

    @CreationTimestamp
    private Timestamp createDt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "member_no")
    private Member member;

    /**
     * 생성자
     * @param query
     * @param sort
     * @param category
     * @param target
     * @param member
     */
    @Builder
    public SearchHistory(String query, String sort, String category, String target, Member member) {
        this.query = query;
        this.sort = sort;
        this.category = category;
        this.target = target;
        this.member = member;
    }
}
