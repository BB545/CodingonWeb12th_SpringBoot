package codingon_kdt.spring_boot_security.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

// PasswordEncoder 객체를 Bean 으로 생성
// -> 스프링에서 의존성 주입을 통해 객체를 관리할 수 있음
