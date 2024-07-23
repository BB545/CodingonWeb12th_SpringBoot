package codingon_kdt.spring_boot_security.security;

import ch.qos.logback.core.util.StringUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component // Spring 컴포넌트로 등록
// 요청당 한번만 실행하는 필터
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // OncePerRequestFilter : 한 요청당 반드시 한번만 실행되는 필터

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = parseBearerToken(request);
            log.info("JwtAuthenticationFilter is running...");

            // token 검사
            // equalsIgnoreCase : 대소문자 가리지 않고 null 이라는 글자 찾음
            if (token != null && !token.equalsIgnoreCase("null")) {
                // userId 가져오기
                String userId = tokenProvider.validateAndGetUserId(token);
                log.info("Authenticated user ID: " + userId);

                // 이전에 추출한 userId 로 인증 객체 생성
                AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, AuthorityUtils.NO_AUTHORITIES);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 생성한 인증 객체를 SecurityContext 에 설정
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }
        
        // 필터 체인을 계속 진행
        filterChain.doFilter(request, response);
    }

    // 요청 헤더에서 Bearer 토큰을 가져옴
    private String parseBearerToken(HttpServletRequest request) {
        // req.header 에
        // Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaXNzIjoiZGVtbyBhcHAiLCJpYXQiOjE3MjE3MDQzNjQsImV4cCI6MTcyMTc5MDc2NH0.fZfsku9QALIfGOhevdDdDXmKyL4kV_mAbSrycmDqfko0hsgIej7B8OGn_llQnKjYumXY3cyJYwXRpzD0Ux28CA
        // 이렇게 저장되어 있고, request.getHeader("Authorization"); -> Bearer 부터 끝까지 잡힘
        String bearerToken = request.getHeader("Authorization");

        // Bearer 로 시작 시 7글자 떼고 뒷부분만 가져오겠다. -> 토큰 부분만 가져와짐
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
}
