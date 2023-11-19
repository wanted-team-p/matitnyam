package com.wandted.matitnyam.util;

import com.wandted.matitnyam.security.HttpProperties;
import com.wandted.matitnyam.security.token.TokenProperties;
import jakarta.servlet.http.Cookie;

public class SetCookieUtil {

    public static Cookie setTokenCookie(String token, boolean isAccessToken){
        int hoursToSeconds = 60 * 60;
        Cookie tokenCookie = new Cookie(
                isAccessToken? HttpProperties.ACCESS_TOKEN_COOKIE : HttpProperties.REFRESH_TOKEN_COOKIE,
                token
        );

        if(!isAccessToken){
            tokenCookie.setMaxAge(TokenProperties.REFRESH_TOKEN_DURATION * hoursToSeconds);
        }

        tokenCookie.setSecure(true);
        tokenCookie.setHttpOnly(true);
        tokenCookie.setPath("/");

        return tokenCookie;
    }
}
