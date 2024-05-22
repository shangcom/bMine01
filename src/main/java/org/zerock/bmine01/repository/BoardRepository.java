package org.zerock.bmine01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.bmine01.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
