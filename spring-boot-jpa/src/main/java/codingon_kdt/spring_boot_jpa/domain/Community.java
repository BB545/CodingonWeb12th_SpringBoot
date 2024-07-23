package codingon_kdt.spring_boot_jpa.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="community")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 100)
    private String content;

    @Column(nullable = false, length = 10)
    private String writer;

    // 데이터베이스에 name 명을 갖는 column 에 값이 저장됨, 값 변경 불가 설정, columnDefinition = 이 외의 설정 값
    @Column(name = "registered", updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime registeredAt;

    @PrePersist // 엔티티가 데이터베이스에 저장되기 전에 필요한 초기화 작업 수행
    protected void onCreate() {
        // 엔티티가 처음 저장될 때 createdAt 필드에 현재 시각을 자동 저장
        // 메서드 이름 자유롭게 설정 가능 (단, 메서드 반환 타입은 void, 매개변수 가질 수 없음)
        registeredAt = LocalDateTime.now();
    }
}
