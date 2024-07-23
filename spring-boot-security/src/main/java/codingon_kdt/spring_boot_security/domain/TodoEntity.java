package codingon_kdt.spring_boot_security.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Todo")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name="userId", nullable = false)
    private String userId;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="done", nullable = false)
    private boolean done;
}
