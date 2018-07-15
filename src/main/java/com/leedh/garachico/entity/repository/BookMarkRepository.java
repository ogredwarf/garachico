package com.leedh.garachico.entity.repository;

import com.leedh.garachico.entity.model.BookMark;
import com.leedh.garachico.entity.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 설명:
 * Project: garachico
 * CLASS: BookMakrRepository
 * User: 이동훈
 * Date: 2018-07-08
 */
@Repository
public interface BookMarkRepository extends JpaRepository<BookMark, Long > {

    /*북마크 여부 확인*/
    Boolean existsByMemberAndIsbn( Member member, final String isbn);

    /* 사용자 별로 즐겨찾기 정보 조회 */
    List<BookMark> findAllByMember(Member member);

    /*삭제*/
    void deleteAllByMemberAndIsbn(Member member, final String isbn);

}
