package codingon_kdt.spring_boot_security.service;

import codingon_kdt.spring_boot_security.domain.TodoEntity;
import codingon_kdt.spring_boot_security.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
// Simple Logging Facade for Java
// - 로그 라이브러리
// - 용도에 따라서 info, debug, warn, error 나눠서 로깅
// - 로깅을 하고 싶은 클래스에 해당 어노테이션을 작성하면 됨
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    // create todo
    public List<TodoEntity> create(final TodoEntity entity) {
        validate(entity); // 유효성 검사
        repository.save(entity);
        log.info("Entity Id: {} is saved", entity.getId());
        return repository.findByUserId(entity.getUserId());
    }

    // read todo
    public List<TodoEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }

    private void validate(final TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown user");
            throw new RuntimeException("Unknown user");
        }
    }

    // update todo
    public List<TodoEntity> update(final TodoEntity entity) {
        // Optional 클래스 : null 일 수 있는 객체를 감싸는 클래스 (안전한 null 처리 가능)
        // - isPresent() 메서드 : Optional 한 객체가 값을 포함하는 지를 확인 (값이 있으면 true, null 이면 false)
        // - get() 메서드 : Optional 객체 내부에 저장된 값 반환
        Optional<TodoEntity> optionalTodo = repository.findById(entity.getId());
        if (optionalTodo.isPresent()) {
            TodoEntity todo = optionalTodo.get();

            if (todo.getUserId().equals(entity.getUserId())) {
                todo.setTitle(entity.getTitle());
                todo.setDone(entity.isDone());
                repository.save(todo);
            } else {
                log.warn("Update failed. User {} is not the owner of the Todo {}", entity.getUserId(), entity.getId());
                throw new RuntimeException("You don't have permission to update this todo");
            }
        } else {
            log.warn("Todo not found with id: {}", entity.getId());
            throw new RuntimeException("Todo not found");
        }

        return retrieve(entity.getUserId());
    }

    // delete todo
    public List<TodoEntity> delete(final TodoEntity entity) {
        try {
            // 해당 id 의 todo 항목을 조회
            Optional<TodoEntity> originalTodo = repository.findById(entity.getId());

            if (originalTodo.isPresent()) {
                TodoEntity todo = originalTodo.get();

                // 현재 사용자의 userId 와 todo 항목의 userId 가 일치하는 지 확인
                if (todo.getUserId().equals(entity.getUserId())) {
                    repository.delete(entity);
                } else {
                    log.warn("Delete failed. User {} is not the owner of the Todo {}", entity.getUserId(), entity.getId());
                    throw new RuntimeException("You don't have permission to delete this todo");
                }
            } else {
                log.warn("Todo not found with id: {}", entity.getId());
                throw new RuntimeException("Todo not found");
            }
        } catch (Exception e) {
            log.error("error deleting entity", entity.getId(), e);
            throw new RuntimeException("error deleting entity " + entity.getId());
        }

        return retrieve(entity.getUserId());
    }
}
