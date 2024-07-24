package codingon_kdt.spring_boot_security.controller;

import codingon_kdt.spring_boot_security.domain.UserEntity;
import codingon_kdt.spring_boot_security.dto.ResponseDTO;
import codingon_kdt.spring_boot_security.dto.UserDTO;
import codingon_kdt.spring_boot_security.security.TokenProvider;
import codingon_kdt.spring_boot_security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth") // 기본 매핑 경로
public class UserController {
    @Autowired
    private UserService userService;

    // [after] jwt token 적용한 후
    @Autowired
    private TokenProvider tokenProvider;

    // [after] 암호화 적용 후
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    // ResponseEntity: 사용자에게 좀 더 응답을 편하게 해주고자 사용
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            // 요청으로 받은 DTO 를 userEntity 로 변환 - 다른 계층 타입 일치 위해
            // [before]
            /* UserEntity user = UserEntity.builder()
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .build(); */
            // [after] 암호화 적용 후
            UserEntity user = UserEntity.builder()
                    .email(userDTO.getEmail())
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword())) // 암호화된 비번으로 user 객체 생성
                    .build();

            // 서비스 계층을 이용해 레포지토리에 저장
            UserEntity registeredUser = userService.create(user);

            // 저장된 사용자 정보로 UserDTO 를 생성해 응답 본문에 담아 리턴
            // password 는 보안상 일부러 보내지 않음
            UserDTO responseUserDTO = UserDTO.builder()
                    .email(registeredUser.getEmail())
                    .id(registeredUser.getId())
                    .username(registeredUser.getUsername())
                    .build();

            return ResponseEntity.ok().body(responseUserDTO);
        } catch (Exception e) {
            // 사용자 정보는 항상 하나이므로 성공 데이터에 대해 리스트로 만들어야 하는 responseDTO 를 사용하지 않고 그냥 userDTO 를 반환
            // 예외 상황에서는 error 메세지를 보여주기 위해 responseDTO 를 사용함
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();

            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
        // UserService 계층을 통해 이메일과 비밀번호로 사용자를 조회
        // [before]
        // UserEntity user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword());
        // [after] 암호화 적용 후
        UserEntity user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword(), passwordEncoder);

        if (user != null) {
            // [before]
            /* final UserDTO responseUserDTO = UserDTO.builder()
                    .email(user.getEmail())
                    .id(user.getId())
                    .build(); */

            // [after] jwt token 적용한 후
            final String token = tokenProvider.create(user);
            final UserDTO responseUserDTO = UserDTO.builder()
                    .email(user.getEmail())
                    .id(user.getId())
                    .token(token) // jwt token 추가
                    .build();

            return ResponseEntity.ok().body(responseUserDTO);
        } else {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error("Login failed")
                    .build();

            // ResponseDTO 에 정의해둔 error 메세지 이용해서 표시
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
