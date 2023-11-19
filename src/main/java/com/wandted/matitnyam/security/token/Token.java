package com.wandted.matitnyam.security.token;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Token {
    private String accessToken;
    private String refreshToken;
}
