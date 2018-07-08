package com.leedh.garachico.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
    private Long markId;

    @Column(nullable = false)
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

}
