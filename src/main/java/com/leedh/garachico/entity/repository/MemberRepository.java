package com.leedh.garachico.entity.repository;

import com.leedh.garachico.entity.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 멤버 관리 Repository
 * MemberRepository
 * project: garachico
 * User: 이동훈
 * Date: 2018-07-07
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 사용자 검색
     */
    Member findOneByUsername( String username );

    /**
     * 사용자 삭제
     * @param username 삭제 대상 ID
     */
    void deleteByUsername(String username);
}
