package codingon_kdt.spring_boot_security.security;

import codingon_kdt.spring_boot_security.config.jwt.JwtProperties;
import codingon_kdt.spring_boot_security.domain.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

// 사용자 정보를 받아서 JWT 생성
@Slf4j
// 컨트롤러 계층에서 의존성을 주입받아 사용할 것이기 떄문에 Service 어노테이션 붙여줌
@Service
public class TokenProvider {
    // [after] 프로퍼티 설정 후
    @Autowired
    private JwtProperties jwtProperties;
    // [before]
    // private static final String SECRET_KEY = "codingon-kdt-security-1523i5";
    
    // create() : JWT 생성
    public String create(UserEntity userEntity) {
        // 토큰 만료 시간을 현재 시간으로 부터 1일 후로 설정
        Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        // JWT 토큰 생성
        /* return Jwts.builder()
                // signWith : HS512 알고리즘과 비밀키로 서명
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                // setSubject : 사용자 ID 를 토큰의 제목으로 설정
                .setSubject(String.valueOf(userEntity.getId()))
                // setIssuer : JWT 토큰 발급자 "demo app"으로 설정한 것임. 바꿔도 상관없음
                .setIssuer("demo app")
                // setIssuedAt : 토큰 발급 시간을 현재시간으로 설정
                .setIssuedAt(new Date())
                // setExpiration : 토큰 만료 시간
                .setExpiration(expiryDate)
                .compact(); // 최종 JWT 문자열 생성 */

        // [after] 프로퍼티 설정 후
        return Jwts.builder()
                // signWith : HS512 알고리즘과 비밀키로 서명
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey())
                // setSubject : 사용자 ID 를 토큰의 제목으로 설정
                .setSubject(String.valueOf(userEntity.getId()))
                // setIssuer : JWT 토큰 발급자 "demo app"으로 설정한 것임. 바꿔도 상관없음
                .setIssuer(jwtProperties.getIssuer())
                // setIssuedAt : 토큰 발급 시간을 현재시간으로 설정
                .setIssuedAt(new Date())
                // setExpiration : 토큰 만료 시간
                .setExpiration(expiryDate)
                .compact();
    }

    // validateAndGetUserId() : 토큰 디코딩 및 파싱하고 토큰 위조 여부를 확인 후 사용자 아이디 리턴
    public String validateAndGetUserId(String token) {
        // Jwts.parser() : JWT 파서를 생성
        // [before]
        /* Claims claims = Jwts.parser()
                // setSigningKey() : 서명 검증을 위해 비밀키를 설정
                .setSigningKey(SECRET_KEY)
                // parseClaimsJws() : 토큰을 파싱하고 서명을 검증 -> 토큰이 위조되면 예외 발생
                .parseClaimsJws(token)
                // 검증된 토큰의 본문(claims) 을 가져옴
                .getBody(); */

        // [after] 프로퍼티 설정 후
        Claims claims = Jwts.parser()
                // setSigningKey() : 서명 검증을 위해 비밀키를 설정
                .setSigningKey(jwtProperties.getSecretKey())
                // parseClaimsJws() : 토큰을 파싱하고 서명을 검증 -> 토큰이 위조되면 예외 발생
                .parseClaimsJws(token)
                // 검증된 토큰의 본문(claims) 을 가져옴
                .getBody();

        // claims 에서 subject (사용자 ID) 를 추출하여 반환
        return claims.getSubject();
    }
}
