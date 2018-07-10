package com.leedh.garachico.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 설명:
 * Project: garachico
 * CLASS: MemberRole
 * User: 이동훈
 * Date: 2018-07-10
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity(name = "tlb_member_role")
@ToString
@Builder
public class MemberRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long memberRoleId;

    @Column(nullable = false, unique = true )
    private String memberRoleName;

    @ManyToOne
    @JoinColumn(name="member_no")
    private Member member;
}
