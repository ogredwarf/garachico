package com.leedh.garachico.service;

import com.leedh.garachico.entity.model.Member;
import com.leedh.garachico.entity.model.SearchHistory;
import com.leedh.garachico.entity.repository.SearchHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 설명: 최근 검색 결과 조회 서비스
 * Project: garachico
 * CLASS: SearchHistoryService
 * User: 이동훈
 * Date: 2018-07-13
 */

@Slf4j
@Service
public class SearchHistoryService {

    @Autowired
    SearchHistoryRepository searchHistoryRepository;

    /**
     * 최근 10건의 조회 기록 부르기
     * ID 가 Sequence 로 생성 되기 때문에 ID 기준으로 처리한다.
     */
    public List<SearchHistory> getLast10SearchHistoryList(Member member) {
        Sort sort = new Sort(Sort.Direction.DESC, "srchId");
        return searchHistoryRepository.findFirst10ByMember(member, sort);
    }


    /* 히스토리 저장 */
    public void save(final SearchHistory history) {
        searchHistoryRepository.save(history);
    }

    /* 히스토리 저장 */
    public void save(final String query, final String sort, final Member member) {
        searchHistoryRepository.save(SearchHistory.builder().query(query).sort(sort).member(member).build());
    }

    /* 히스토리 저장 */
    public void save(final String query, final String sort, final String category, final String target, final Member member) {

        searchHistoryRepository.save(SearchHistory.builder()
                .query(query)
                .sort(sort)
                .category(category)
                .target(target)
                .member(member)
                .build());
    }


}
