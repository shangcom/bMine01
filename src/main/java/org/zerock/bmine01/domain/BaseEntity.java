package org.zerock.bmine01.domain;



import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
// @EntityListeners : 엔티티 생명주기 이벤트 처리할 클래스 지정. value={배열} 생략 가능. 바로 클래스명만 적어도 됨. 복수일 때는 중괄호는 생략 불가(배열)
// AuditingEntityListener : 엔티티가 DB에 추가/변경될 때 자동으로 시간 값을 지정.
@Getter
abstract class BaseEntity {

    @CreatedDate
    @Column(name = "regdate", updatable = false) // 컬럼 명은 regdate로, 내용 변경 불가.
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;

//    @CreatedDate
//    @Column(updatable = false)
//    private LocalDateTime regDate;
//
//    @LastModifiedDate
//    private LocalDateTime modDate;
//    이렇게 해도 같음. name 속성을 생략하면 JPA는 필드 이름을 데이터베이스 컬럼 이름으로 사용함. @Column(name="")에서 이름 지정할 필요 없다.

}
