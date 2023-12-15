package com.wanted.matitnyam.service;

import com.wanted.matitnyam.domain.Authority;
import com.wanted.matitnyam.dto.PrincipalDto;
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

        String authorityAsString = claimsJws.getPayload().get("auth").toString();
        Authority authority = Authority.convertFromString(authorityAsString);
        String name = claimsJws.getPayload().get("sub").toString();
        Double latitude = Double.parseDouble(claimsJws.getPayload().get("latitude").toString());
        Double longitude = Double.parseDouble(claimsJws.getPayload().get("longitude").toString());
        return PrincipalDto.builder()
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .authority(authority)
                .build();
    }

    public SecretKey getSecretKey() {
        final String secretAlgorithm = "HmacSHA256";
        byte[] secretKeyAsBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(secretKeyAsBytes, secretAlgorithm);
    }

}