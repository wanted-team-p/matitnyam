package com.wandted.matitnyam.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class Member {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long seq;

    @NotBlank
    String name;

    @NotBlank
    String password;

    @Enumerated(EnumType.STRING)
    Authority authority;

    @Builder
    public Member(final String name, final String password) {
        this.name = name;
        this.password = password;
        this.authority = Authority.USER;
    }

}
