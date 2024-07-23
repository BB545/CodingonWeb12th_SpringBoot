package codingon_kdt.spring_boot_security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO<T> {
    private String error;
    // Generic 을 이용해 200 status code 로 응답 시 해당 type 의 리스트를 반환하도록
    private List<T> data;
}
