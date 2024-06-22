package org.zerock.bmine01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.bmine01.dto.BoardDTO;
import org.zerock.bmine01.dto.PageRequestDTO;
import org.zerock.bmine01.dto.PageResponseDTO;
import org.zerock.bmine01.service.BoardService;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
////
    private final BoardService boardService;

    //list 메서드는 아무 것도 반환하지 않지만, 뷰 이름을 명시하지 않을 경우,
    // Spring은 기본적으로 요청 경로(/board/list)를 템플릿 경로로 해석하여
    // src/main/resources/templates/board/list.html 템플릿을 찾음.
    // localhost:8080/board/list를 요청할 때, list.html 템플릿에서 responseDTO 데이터를 사용할 수 있게 됨.
    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        // 파라미터 바인딩 : HTTP 요청 파라미터는 스프링이 자동으로 컨트롤러 메서드의 매개변수 객체에 매핑함.
        // 여기서는 PageRequestDTO, Model 객체가 자동으로 생성된다.
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
        return "board/list";
        // responseDTO를 view(list.html 템플릿)에 전달됨.
    }

}
