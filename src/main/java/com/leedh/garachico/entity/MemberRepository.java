package com.leedh.garachico.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * project: garachico
 * User: 이동훈
 * Date: 2018-07-07
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    /**
     * ID로 파일 검색
     */
    Member findOneByUserId( String userId );
}
