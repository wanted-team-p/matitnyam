package com.wandted.matitnyam.security;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthenticationToken extends AbstractAuthenticationToken {
    private final String id;
    private final String username;
    private final Long latitude;
    private final Long longitude;
    private final Boolean useRecommendLunch;

    public CustomAuthenticationToken(String id, String username, Long latitude, Long longitude, Boolean useRecommendLunch, Collection<? extends GrantedAuthority> authorities){
        super(authorities);
        this.id = id;
        this.username = username;
        this.latitude = latitude;
        this.longitude = longitude;
        this.useRecommendLunch = useRecommendLunch;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return id;
    }
}
