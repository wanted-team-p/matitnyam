package com.wandted.matitnyam.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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

    public Member(final String name, final String password) {
        this.name = name;
        this.password = password;
    }

}
