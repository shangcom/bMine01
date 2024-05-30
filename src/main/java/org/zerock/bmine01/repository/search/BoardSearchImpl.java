package org.zerock.bmine01.repository.search;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.bmine01.domain.Board;
import org.zerock.bmine01.domain.QBoard;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {


    /*QuerydslRepositorySupport 클래스의 생성자를 호출.
     * QuerydslRepositorySupport 클래스는 특정 엔티티와 연결된 설정을 초기화하는
     * 생성자가 필요함. super(Board.class) 호출을 통해 QuerydslRepositorySupport
     * 클래스는 Board 엔티티와 관련된 설정을 수행함.
     */
    public BoardSearchImpl() {
        super(Board.class);
    }


    //search1() 메서드는 QueryDSL을 활용하여 Board 엔티티의 title 필드에 특정 문자열이 포함된 항목을 검색하고,
    // 페이지네이션된 결과를 반환.
    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board; // QBoard 클래스의 정적 필드 board.
        JPQLQuery<Board> query = from(board);
        //from(board) 메서드를 사용하여 JPQLQuery 객체 생성. Board 엔티티에 대한 쿼리를 작성할 수 있게 해줌.
        query.where(board.title.contains("1"));
        //where 메서드를 사용하여 검색 조건 추가. title 필드에 "1"이 포함된 Board 엔티티를 찾음.
        this.getQuerydsl().applyPagination(pageable, query);
        //applyPagination() 메서드를 사용하여 pageable 객체에 정의된 페이지네이션 설정을 쿼리에 적용.
        List<Board> list = query.fetch();
        // fetch 메서드를 사용하여 쿼리를 실행하고 결과를 리스트로 반환.
        long count = query.fetchCount();
        //fetchCount 메서드를 사용하여 총 결과 수를 가져옴.
        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        // 여기서부터
        return null;
    }
}


/*
커스텀 리포지토리 구현 클래스 (BoardSearchImpl)
BoardSearch 인터페이스를 구현하는 클래스. 여기서는 QuerydslRepositorySupport를 확장하여 QueryDSL 기능을 사용할 수 있게 함.

QuerydslRepositorySupport: QueryDSL을 사용할 수 있도록 지원하는 클래스. QueryDSL을 사용하여 JPA 쿼리를 더 쉽게 작성할 수 있도록 도와줌.
BoardSearchImpl(): Board 엔티티를 지원하는 생성자.
search1(Pageable pageable): search1 메서드의 실제 구현. 실제 쿼리를 작성하여 구현할 필요가 있습니다.
Spring Data JPA는 기본적으로 제공하는 CRUD 메서드 외에도 커스텀 쿼리 메서드를 정의하고 구현할 수 있게 함.
 BoardSearchImpl 클래스는 이러한 커스텀 메서드를 구현하기 위한 것임.
*/