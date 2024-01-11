package com.wanted.matitnyam.domain;

public enum Authority {

    USER,
    ADMIN;

    public static Authority convertFromString(String givenString) {
        for (Authority authority : Authority.values()) {
            if (givenString.equals(authority.toString())) {
                return authority;
            }
        }
        return null;
    }
}
