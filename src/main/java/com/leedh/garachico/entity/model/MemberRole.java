package com.leedh.garachico.entity.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * 설명: 사용자 권한 설정 테이블
 * Project: garachico
 * CLASS: MemberRole
 * User: 이동훈
 * Date: 2018-07-10
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "tlb_member_role")
@ToString
public class MemberRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long memberRoleId;

    @Column(nullable = false, unique = true )
    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }

    @Builder
    public MemberRole( String roleName){
        this.roleName = roleName;
    }
}
