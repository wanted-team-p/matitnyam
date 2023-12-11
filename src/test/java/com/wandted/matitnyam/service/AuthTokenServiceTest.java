package com.wandted.matitnyam.service;

import com.wandted.matitnyam.domain.Authority;
import com.wandted.matitnyam.dto.PrincipalDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthTokenServiceTest {

    @Autowired
    AuthTokenService authTokenService;

    private final String name = "neppiness";

    @DisplayName("토큰 생성 테스트")
    @Test
    void createTokenTest() {
        PrincipalDto principal = PrincipalDto.builder()
                .name(name)
                .authority(Authority.USER)
                .build();
        System.out.println(authTokenService.createToken(principal));
    }

    @DisplayName("유효한 토큰 파싱 테스트")
    @Test
    void parseValidTokenTest() {
        PrincipalDto givenPrincipal = PrincipalDto.builder()
                .name(name)
                .authority(Authority.USER)
                .build();
        String token = authTokenService.createToken(givenPrincipal);
        System.out.println("token = " + token);

        PrincipalDto parsedPrincipal = authTokenService.parseToken(token);
        Assertions.assertThat(parsedPrincipal.getName()).isEqualTo(name);
        Assertions.assertThat(parsedPrincipal.getAuthority()).isEqualTo(Authority.USER);
    }

    @DisplayName("유효하지 않은 토큰 파싱 테스트")
    @Test
    void parseInvalidTokenTest() {
        String invalidToken = "eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9"
                + ".eyJzdWIiOiJuZXBwaW5lc3MiLCJpYXQiOjE3MDA5ODQ4NjEsImV4cCI6MTcwMDk4NjY2MSwiYXV0aCI6IlVTRVIifQ"
                + ".u5wOyEeDh-68d10XeAmGwnbsLbVjSUu1ICpxe0o6tLP";

        Assertions.assertThatThrownBy(() -> authTokenService.parseToken(invalidToken))
                .isInstanceOf(SignatureException.class);
    }

    @DisplayName("만료된 토큰 테스트")
    @Test
    void parseExpiredTokenTest() {
        /*
        HEADER: { "typ": "jwt", "alg": "HS256" }
        PAYLOAD: { "sub": "neppiness", "iat": 1500984861, "exp": 1500986661, "auth": "USER" }
         */
        String expiredToken = "eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9"
                + ".eyJzdWIiOiJuZXBwaW5lc3MiLCJpYXQiOjE1MDA5ODQ4NjEsImV4cCI6MTUwMDk4NjY2MSwiYXV0aCI6IlVTRVIifQ"
                + ".ozmVVI40aidztXYkjefxAomvUXIs1eJesqXu_RYUTIU";

        Assertions.assertThatThrownBy(() -> authTokenService.parseToken(expiredToken))
                .isInstanceOf(ExpiredJwtException.class);
    }

}