package org.zerock.bmine01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.bmine01.domain.Board;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        IntStream.range(1, 100).forEach(i -> {
            Board board = Board.builder()
                    .title("title..." + i)
                    .content("content..." + i)
                    .writer("user" + i % 10)
                    .build();

            Board result = boardRepository.save(board);
            log.info("BNO: " + result.getBno());
        });
    }

    @Test
    public void testSelect() {
        Long bno = 100L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        log.info(board);
    }

    @Test
    public void testUpdate() {
        Long bno = 99L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        board.change("Update... title 99", "Update content 99");

        boardRepository.save(board);

        log.info(board);
    }

    @Test
    public void testDelete() {
        Long bno = 3L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        log.info(board);

        boardRepository.deleteById(bno);
    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);

        log.info("total count : " + result.getTotalElements());
        log.info("total pages : " + result.getTotalPages());
        log.info("page number : " + result.getNumber());
        log.info("page size : " + result.getSize());

        List<Board> todoList = result.getContent();
        todoList.forEach(board -> log.info(board));

    }

    @Test
    public void testKeywordSearch() {
        String keyword = "update...";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> todoList = boardRepository.findKeyword(keyword, pageable);
        todoList.forEach(board -> log.info(board));
    }

    @Test
    public void testGetTime() {
        log.info(boardRepository.getTime());
    }

    @Test
    public void testSearch1() {

        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
        boardRepository.search1(pageable);
    }

    @Test
    public void testSearchAll() {

        String[] types = {"t", "c", "w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        // 첫 번째 페이지에서 10개의 레코드를 내림차순으로 정렬해서 가져옴.
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);
        log.info("제목, 내용, 작성자를 포함하여 1이라는 키워드를 가지고 있는 레코드들 전부 출력");
        log.info("1을 가진 전체 페이지 수 : " + result.getTotalPages());
        log.info("페이지의 크기 : " + result.getSize());
        log.info("현재 페이지 번호 : " + result.getNumber());
        log.info("이전 페이지 존재? " + result.hasPrevious() + " / " + "다음 페이지 존재? " + result.hasNext());
        result.getContent().forEach(board -> log.info(board));
    }


}
