package codingon_kdt.spring_boot_jpa.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴 사용
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private int no;
}
