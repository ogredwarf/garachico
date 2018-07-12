package com.leedh.garachico.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

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

    @Column(nullable = false, unique = true, length = 60)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @UpdateTimestamp
    private Timestamp lastModifyDt;

    @CreationTimestamp
    private Timestamp createDt;

    @OneToMany( cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "member_no")
    private List<MemberRole> roles;

    @Builder
    public Member(String username, String pswd, String role ) {
        this.username = username;
        this.password = pswd;
        this.roles = Arrays.asList( MemberRole.builder().roleName(role).build() );
    }

}
