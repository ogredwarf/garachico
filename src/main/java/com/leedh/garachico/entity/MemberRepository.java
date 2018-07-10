package com.leedh.garachico.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MemberRepository
 * project: garachico
 * User: 이동훈
 * Date: 2018-07-07
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    /**
     * ID로 파일 검색
     */
    Member findOneByUsername( String username );
}
