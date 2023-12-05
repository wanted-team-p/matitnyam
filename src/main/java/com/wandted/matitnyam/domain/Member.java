package com.wandted.matitnyam.domain;

import com.wandted.matitnyam.dto.CoordinatesRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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

    Double latitude;

    Double longitude;

    @Builder
    public Member(final String name, final String password) {
        this.name = name;
        this.password = password;
        this.authority = Authority.USER;
    }

    public void setCoordinates(final CoordinatesRequest coordinatesRequest) {
        this.latitude = coordinatesRequest.getLatitude();
        this.longitude = coordinatesRequest.getLongitude();
    }

}
