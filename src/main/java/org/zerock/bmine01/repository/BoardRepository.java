package org.zerock.bmine01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.bmine01.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b where b.title like concat('%',:keyword,'%') ")
    Page<Board> findkeyword(String keyword, Pageable pageable);

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
