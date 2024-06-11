package org.zerock.bmine01.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {

    private static final int PAGE_DISPLAY_COUNT = 10;
    private int page;
    private int size;
    private int total;
    private int start;
    private int end;
    private boolean prev;
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
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

        int last = (int) (Math.ceil(total / (double) size));

        this.end = Math.min(end, last);

        this.prev = this.start > 1;
        this.next = total > this.end * this.size;
        // this.next = last > this.end; // 이거랑 같음.
    }


}
