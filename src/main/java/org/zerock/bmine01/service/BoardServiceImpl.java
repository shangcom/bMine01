    package org.zerock.bmine01.service;

    import lombok.RequiredArgsConstructor;
    import lombok.extern.log4j.Log4j2;
    import org.modelmapper.ModelMapper;
    import org.springframework.stereotype.Service;
    import org.zerock.bmine01.domain.Board;
    import org.zerock.bmine01.dto.BoardDTO;
    import org.zerock.bmine01.repository.BoardRepository;

    import javax.transaction.Transactional;
    import java.util.Optional;

    @Service
    @Log4j2
    @RequiredArgsConstructor
    @Transactional
    public class BoardServiceImpl implements BoardService {

    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

        @Override
        public Long register(BoardDTO boardDTO) {
            Board board = modelMapper.map(boardDTO, Board.class);
            Long bno = boardRepository.save(board).getBno();
            return bno;
        }

        @Override
        public BoardDTO readOne(Long bno) {
            Optional<Board> result = boardRepository.findById(bno);
            Board board = result.orElseThrow();
            BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
            return boardDTO;
        }

        @Override
        public void modify(BoardDTO boardDTO) {
            Optional<Board> result = boardRepository.findById(boardDTO.getBno());
            Board board = result.orElseThrow();
            board.change(boardDTO.getTitle(), boardDTO.getContent());
            boardRepository.save(board);
        }

        @Override
        public void remove(Long bno) {
//            Optional<Board> result = boardRepository.findById(bno);
//            Board board = result.orElseThrow();
            // 필요 없음. deleteById() 메서드에 삭제할 대상 엔티티가 없으면 EmptyResultDataAccessException 발생시킴.
            boardRepository.deleteById(bno);
            // deleteById() 메서드는 대상이 없으면 예외를 던진다. 따라서 Optional로 받아주지 않아도 된다.
        }
    }
