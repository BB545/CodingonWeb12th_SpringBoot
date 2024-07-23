package codingon_kdt.spring_boot_security.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="User", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
// uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
// - email 필드에 유니크 제약조건 추가
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id; // NULL 될 수 없음

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email; // UNIQUE = NULL 될 수 있음

    @Column(name = "password", nullable = false)
    private String password;
}
