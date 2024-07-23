package codingon_kdt.spring_boot_jpa.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String email;

    // 데이터베이스에 name 명을 갖는 column 에 값이 저장됨, 값 변경 불가 설정, columnDefinition = 이 외의 설정 값
    @Column(name = "created_at", nullable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @PrePersist // 엔티티가 데이터베이스에 저장되기 전에 필요한 초기화 작업 수행
    protected void onCreate() {
        // 엔티티가 처음 저장될 때 createdAt 필드에 현재 시각을 자동 저장
        // 메서드 이름 자유롭게 설정 가능 (단, 메서드 반환 타입은 void, 매개변수 가질 수 없음)
        createdAt = LocalDateTime.now();
    }
}

// mybatis schema.sql 와 비교
// users 테이블 생성
/* create table users (
        id bigint auto_increment primary key,
        username varchar(50) not null,
email varchar(100) not null,
created_at timestamp default current_timestamp
); */
