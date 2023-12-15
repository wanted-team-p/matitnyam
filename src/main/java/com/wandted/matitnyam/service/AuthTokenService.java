package com.wandted.matitnyam.service;

import com.wandted.matitnyam.domain.Authority;
import com.wandted.matitnyam.dto.PrincipalDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {

    @Value("${spring.config.jwt.secret-key}")
    private String secretKey;

    public String createToken(PrincipalDto principal) {
        final long TOKEN_DURATION_IN_MILLIS = 30 * 60 * 1000L;
        Date now = new Date();

        Claims claims = Jwts.claims()
                .subject(principal.name())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + TOKEN_DURATION_IN_MILLIS))
                .add("auth", principal.authority())
                .add("latitude", principal.latitude())
                .add("longitude", principal.longitude())
                .build();

        return Jwts.builder()
                .header()
                .type("jwt")
                .and()
                .claims(claims)
                .signWith(getSecretKey())
                .compact();
    }

    public PrincipalDto parseToken(String token) {
        Jws<Claims> claimsJws =  Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token);

        String authorityInString = claimsJws.getPayload().get("auth").toString();
        Authority authority = Authority.convertFromString(authorityInString);
        return PrincipalDto.builder()
                .name(claimsJws.getPayload().get("sub").toString())
                .authority(authority)
                .build();
    }

    public SecretKey getSecretKey() {
        final String secretAlgorithm = "HmacSHA256";
        byte[] secretKeyAsBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(secretKeyAsBytes, secretAlgorithm);
    }

}