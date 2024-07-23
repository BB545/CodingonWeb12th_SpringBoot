package codingon.spring_boot_mybatis.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor // 매개변수 없는 기본 생성자
// @AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자
// @Builder // 빌더 패턴 사용가능 하도록
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    // domain.User 와 다르게 "no" 필드가 추가
    // -> 실제 테이블에 존재하지는 않지만, 서비스 로직에서 no 필드를 사용할 예정
    private int no;
}

// dto.UserDTO
// - 데이터 전송 계층(dto) 으로 클라이언트의 서버 간의 데이터 교환에 사용
// - 클라이언트에게 노출하고 싶지 않은 민감한 정보를 User 객체(domain) 에 포함시키고, DTO 변환하는 과정에서 제외시킬 수 있음