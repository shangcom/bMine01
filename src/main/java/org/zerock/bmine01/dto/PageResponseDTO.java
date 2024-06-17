package org.zerock.bmine01.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {

    private static final int PAGE_DISPLAY_COUNT = 10; // 한 화면에 보여줄 페이지 갯수
    private int page; // 현재 페이지 번호
    private int size; // 한 페이지 내의 항목 수
    private int total; // 전체 항목 수
    private int start; // 현재 화면에 표시된 페이지 그룹의 시작 페이지 번호
    private int end; // 현재 화면에 표시된 페이지 그룹의 마지막 페이지 번호
    private boolean prev; // 현재 페이지 그룹의 앞에 페이지가 존재하는가?
    private boolean next; // 현재 페이지 그룹의 다음에 페이지가 존재하는가?

    private List<E> dtoList; //

    @Builder(builderMethodName = "withAll") // .build() 대신 withAll()로 호출.
    public PageResponseDTO(PageRequestDTO pageRequestDTO, int total, List<E> dtoList) {

        if (total <= 0) {
            return;
        }
        this.total = total;

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.dtoList = dtoList;

        this.end = (int) (Math.ceil(page / (double)PAGE_DISPLAY_COUNT)) * PAGE_DISPLAY_COUNT;

        this.start = end - (PAGE_DISPLAY_COUNT - 1);

        int last = (int) (Math.ceil(total / (double) size)); // 총 페이지 수 (= 마지막 페이지 번호)

        this.end = Math.min(end, last);

        /*
        * end는 총 페이지 수를 고려하지 않음으로, 마지막 페이지 그룹의 end 값은 부정확할 수 있다.
        * 예를 들어, 전체 항목 수가 50개인 경우 last = 5
        * 그러나 this.end = 10
        * 즉 실제 존재하는 페이지 수보다 end가 더 커진다.
        * 따라서 마지막 페이지 그룹일 경우를 대비 */

//        if (this.end > last) {
//            this.end = last;
//        }
        // 이렇게 조건문으로 하는 것과 마찬가지이나, Math.min이 더 효율적이라고 한다.

        this.prev = this.start > 1;
        this.next = total > this.end * this.size;
        // this.next = last > this.end; // 이거랑 같음.
    }


}
