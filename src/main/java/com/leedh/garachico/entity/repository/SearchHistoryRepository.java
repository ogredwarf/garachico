package com.leedh.garachico.entity.repository;

import com.leedh.garachico.entity.model.Member;
import com.leedh.garachico.entity.model.SearchHistory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 설명:
 * Project: garachico
 * CLASS: SearchHistoryRepository
 * User: 이동훈
 * Date: 2018-07-13
 */
@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    /**
     * 최근 검색 결과 10 건의 정보를 가져온다.
     * @param member
     * @return
     */
    List<SearchHistory> findFirst10ByMember(Member member, Sort sort);

}
