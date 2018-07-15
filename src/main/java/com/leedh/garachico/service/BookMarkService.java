package com.leedh.garachico.service;

import com.leedh.garachico.entity.model.BookMark;
import com.leedh.garachico.entity.model.Member;
import com.leedh.garachico.entity.repository.BookMarkRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 설명: 북마크 서비스 등록
 * Project: garachico
 * CLASS: BookMarkService
 * User: 이동훈
 * Date: 2018-07-15
 */
@Slf4j
@Service
public class BookMarkService {

    @Autowired
    BookMarkRepository bookMarkRepository;

    /**
     * 북마크 추가
     */
    @Transactional
    public Boolean add(@NonNull final String isbn,
                       @NonNull final String title,
                       @NonNull final String url,
                       @NonNull final Member member) {
        log.info("북마크 추가 ");

        if (bookMarkRepository.existsByMemberAndIsbn(member, isbn)) {
            log.info("이미 존재하는 데이터");
            return false;
        }
        log.info("북마크 저장");
        bookMarkRepository.save(BookMark.builder()
                .isbn(isbn)
                .title(title)
                .url(url)
                .member(member)
                .build()
        );
        return true;
    }

    @Transactional
    public Boolean remove(@NonNull final String isbn,
                       @NonNull final String title,
                       @NonNull final String url,
                       @NonNull final Member member) {
        log.info("북마크 삭제 ");

        if (!bookMarkRepository.existsByMemberAndIsbn(member, isbn)) {
            log.info("해당하는 북마크가 없습니다.");
            return false;
        }
        bookMarkRepository.deleteAllByMemberAndIsbn( member, isbn );
        return true;
    }

    /**
     * 북마크 리스트 조회
     */
    public List<BookMark> list(final Member member) {
        return bookMarkRepository.findAllByMember(member);
    }

}
