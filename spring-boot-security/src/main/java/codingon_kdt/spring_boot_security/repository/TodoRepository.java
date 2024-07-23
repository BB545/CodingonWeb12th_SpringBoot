package codingon_kdt.spring_boot_security.repository;

import codingon_kdt.spring_boot_security.domain.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    List<TodoEntity> findByUserId(String userId);
}
