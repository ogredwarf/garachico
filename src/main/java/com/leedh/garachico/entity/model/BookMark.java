package com.leedh.garachico.entity.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 설명:
 * Project: garachico
 * CLASS: BookMark
 * User: 이동훈
 * Date: 2018-07-08
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "tlb_bookmark_m")
@ToString
public class BookMark {

    @Id
    @GeneratedValue
    private Long markId;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @UpdateTimestamp
    private Timestamp lastModifyDt;

    @CreationTimestamp
    private Timestamp createDt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "member_no")
    private Member member;

    @Builder
    public BookMark ( String isbn, String title, Member member) {
        this.isbn = isbn;
        this.title = title;
        this.member = member;
    }

}
