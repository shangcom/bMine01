package org.zerock.bmine01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1; // 현재 페이지(클라이언트가 보고 있는 페이지 / 보고자 하는 페이지)

    @Builder.Default
    private int size = 10; // 페이지 크기 (한 페이지에 있는 요소의 수)

    private String type; // 검색의 종류 t, c, w, tc, tw, cw, twc. Board 엔티티의 필드들.
    // bno, regdate, moddate 등도 가능하나, BoardSearchImpl의 searchAll을 수정해야함.

    private String keyword; // 검색어. 설정한 검색의 종류 중 해당 키워드를 담고 있는 것들을 검색한다.


    private String link; // 페이징과 검색 조건을 문자열로 구성. URL로 전달하기 위해 필요.

    public String[] getTypes() {
        if (type == null || type.isEmpty()) {
            return null;
        }
        return type.split("");
    }

    public Pageable getPageable(String... props) {
        return PageRequest.of(this.page - 1, this.size, Sort.by(props).descending());
    } // this.page -1 :

    public String getLink() {
        if (link == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("page=" + this.page);
            stringBuilder.append("&size=" + this.size);
            if (type != null && type.length() > 0) {
                stringBuilder.append("&type=" + this.type);
            }
            if (keyword != null) {
                try {
                    stringBuilder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
            link = stringBuilder.toString();
        }
        return link;

    }
}
