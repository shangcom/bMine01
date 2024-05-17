package org.zerock.bmine01.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello(Model model) {
        log.info("hello..............");
        model.addAttribute("msg", "Hello world");
    }

    @GetMapping("/ex/ex1")
    public void ex1(Model model) {

        List<String> list = Arrays.asList("AAA", "BBB", "CCC", "DDDD");

        model.addAttribute("list", list);
    }

    class SampleDTO {
        private String p1, p2, p3;

        public String getP1() {
            return p1;
        }

        public String getP2() {
            return p2;
        }

        public String getP3() {
            return p3;
        }
    }

    @GetMapping("/ex/ex2")
    public void ex2(Model model) {
        log.info("/ex/ex2..............");

        List<String> strList = IntStream.range(1,10)
                .mapToObj(i -> "Data" + i)
                .collect(Collectors.toList());

        model.addAttribute("list",strList);

        Map<String, String> map = new HashMap<>();
        map.put("A", "AAA");
        map.put("B", "BBB");

        model.addAttribute("map", map);

        SampleDTO sampleDTO = new SampleDTO();
        sampleDTO.p1 = "Value -- p1";
        sampleDTO.p2 = "Value -- p2";
        sampleDTO.p3 = "Value -- p3";

        model.addAttribute("dto", sampleDTO);
    }

    @GetMapping("/ex/ex3")
    public void ex3(Model model) {

        model.addAttribute("arr", new String[]{"AAA","BBB","CCC"});

    }


    // 블럭 사용하면 개발자도구 - 요소에서 최종 html에 어떻게 달라지는지 보는 예시.
    // 블럭 사용하면 <div> 태그 안 나온다(=랜더링 하지 않는다)
    @GetMapping("/ex/blockEx")
    public String example1(Model model) {
        log.info("blockEx..................");
        List<String> items = Arrays.asList("Item 1", "Item 2", "Item 3");
        model.addAttribute("items", items);
        return "/ex/blockEx"; // This corresponds to "Iteration without th:block"
    }
}
