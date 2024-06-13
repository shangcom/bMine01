package org.zerock.bmine01.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.bmine01.domain.Board;
import org.zerock.bmine01.domain.QBoard;

import javax.persistence.Persistence;
import java.util.List;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
//    QuerydslRepositorySupport: QueryDSL과 JPA 통합을 위한 유틸리티 메서드를 제공하는 추상 클래스.

//    BoardSearch 인터페이스를 구현하며, QueryDSL을 활용한 커스텀 검색 기능을 제공.

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
        QBoard board = QBoard.board; //  QBoard의 필드를 사용하여 Board 엔티티의 각 필드를 참조.
//      QBoard 클래스의 board 필드를 사용하여 QBoard의 단일 인스턴스를 생성. Board 엔티티를 표현하는 QueryDSL 메타모델 클래스임.

        JPQLQuery<Board> query = from(board);
        /* from() : QBoard.board를 기반으로 쿼리의 시작점을 설정. 이 메서드는 JPQLQuery 객체를 생성하고,
        지정된 엔티티 경로(QBoard.board)를 사용하여 쿼리를 초기화함. 이 JPQLQuery 객체는 Board 엔티티에 대한
        JPQL 쿼리를 작성하고 실행하는 데 사용됨.*/

        query.where(board.title.contains("1"));
        //where 메서드를 사용하여 검색 조건 추가. title 필드에 "1"이 포함된 Board 엔티티를 찾음.

        log.info("Generated Query: " + query.toString());

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
        QBoard board = QBoard.board; // QueryDSL을 사용해서 동적쿼리 작성하기 위한 메타 모델 클래스.
        JPQLQuery<Board> query = from(board);

        // 동적으로 조건을 추가하기 위해 BooleanBuilder를 사용
        if ((types != null && types.length > 0) && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            // BooleanBuilder : QueryDSL 제공 클래스. 런타임 동적 쿼리 구성 시 사용. AND, OR, NOT 등 논리식 구현.
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                } // title, content, writer 기준 검색 가능. bno나 regdate, moddate 등도 추가할 수 있음.
            }
            query.where(booleanBuilder);
        }
        query.where(board.bno.gt(0L)); // 기본 조건 추가
//        this.getQuerydsl().applyPagination(pageable, query); // 페이징 적용
        List<Board> list = query.fetch(); // 쿼리 실행해서 결과를 list에 담음.
        long count = query.fetchCount();
        return new PageImpl<>(list, pageable, count); // PageImpl 클래스 : Spring Data JPA가 제공하는 클래스. Page<T> 생성 기능 제공.
        // 페이징 처리의 최종 결과는 Page<T> 타입으로 반환해야 하기 때문에 사용.
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