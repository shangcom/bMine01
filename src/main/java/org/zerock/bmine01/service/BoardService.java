package org.zerock.bmine01.service;

import org.zerock.bmine01.dto.BoardDTO;
import org.zerock.bmine01.dto.PageRequestDTO;
import org.zerock.bmine01.dto.PageResponseDTO;

public interface BoardService {
    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
