package codingon_kdt.spring_boot_default.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter // getter 메서드 자동 생성
@Setter // setter 메서드 자동 생성
public class UserDTO {
    private String name;
    private int age;
}

// UserDTO
// - 요청 시 전달된 값을 담는데에 사용할 객체
