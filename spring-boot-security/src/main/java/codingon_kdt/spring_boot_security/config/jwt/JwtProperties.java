package codingon_kdt.spring_boot_security.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
// 자바 클래스에 프로퍼티 값(application.properties) 를 가져와서 사용하는 어노테이션
@ConfigurationProperties("jwt")
public class JwtProperties {
    private String issuer;
    private String secretKey;
}
