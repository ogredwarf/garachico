package com.leedh.garachico.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Member table 설정
 * project: garachico
 * User: 이동훈
 * Date: 2018-07-07
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "tlb_member_m")
@ToString(exclude = "password") /*비밀번호 보호차원*/
public class Member {

    @Id
    @Column
    @GeneratedValue
    private Long memberNo;

    @Column(nullable = false)
    private String userId;

    @JsonIgnore
    @Column(length = 64, nullable = false)
    private String password;

    @UpdateTimestamp
    private Timestamp lastModifyDt;

    @CreationTimestamp
    private Timestamp createDt;

    @Builder
    public Member(String userId, String pswd ) {
        this.userId = userId;
        this.password = pswd;
    }

}
