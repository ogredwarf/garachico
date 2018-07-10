package com.leedh.garachico.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 설명:
 * Project: garachico
 * CLASS: BookMakrRepository
 * User: 이동훈
 * Date: 2018-07-08
 */
@Repository
public interface BookMarkRepository extends JpaRepository<BookMark, Long > {
}
