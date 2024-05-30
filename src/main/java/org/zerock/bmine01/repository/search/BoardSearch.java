package org.zerock.bmine01.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.bmine01.domain.Board;

public interface BoardSearch {
    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}

/*
커스텀 리포지토리 인터페이스 (BoardSearch)
이 인터페이스는 BoardRepository에 통합됨.
BoardSearch 인터페이스는 커스텀 메서드를 정의함.
여기서는 search1 메서드를 정의했음.
search1(Pageable pageable): 특정 검색 조건에 따라 페이지네이션된 Board 엔티티를 반환하는 메서드 시그니처를 정의.
*/
