package org.zerock.bmine01.repository;

import org.hibernate.sql.Delete;
import org.hibernate.sql.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.bmine01.domain.Board;
import org.zerock.bmine01.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

    @Query("select b from Board b where b.title like concat('%',:keyword,'%') ")
    Page<Board> findKeyword(String keyword, Pageable pageable);

    @Query(value = "select now()", nativeQuery = true)
    String getTime();

/*  기본적인 Spring Data JPA 리포지토리 인터페이스 (BoardRepository)
    BoardRepository는 JpaRepository와 BoardSearch를 확장. 이를 통해 기본적인 JPA 기능과 커스텀 기능을 모두 제공받음.

    JpaRepository : 기본적인 CRUD (Create, Read, Update, Delete) 메서드를 제공하며, 추가로 커스텀 리포지토리 메서드를 정의할 수 있게 함.
    JpaRepository<Board, Long>: Board 엔티티와 그 기본 키 타입인 Long을 사용하는 기본 JPA 리포지토리.

    BoardSearch: 커스텀 리포지토리 인터페이스를 확장하여 커스텀 메서드를 포함. 이를 통해 기본적인 CRUD 외에 사용자 정의
    쿼리 메서드를 구현하고 사용할 수 있음.
    */
}
