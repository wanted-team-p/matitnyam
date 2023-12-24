package com.wanted.matitnyam.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MobilityTest {

    @DisplayName("Mobility enum 파싱 테스트: 정상 입력")
    @ValueSource(strings = {"transportation", "Transportation", "TRANSPORTATION", "waLK", "walk", "Walk"})
    @ParameterizedTest
    void parseMobilityTest(String mobilityAsString) {
        Mobility mobility = Mobility.parseMobility(mobilityAsString);
        Assertions
                .assertThat(mobility)
                .isNotNull();
        System.out.println(mobility.name());
    }

    @DisplayName("Mobility enum 파싱 테스트: 예외 입력")
    @ValueSource(strings = {"taxi", "bus", "subway", "walking"})
    @ParameterizedTest
    void parseMobilityExceptionTest(String mobilityAsString) {
        Mobility mobility = Mobility.parseMobility(mobilityAsString);
        Assertions
                .assertThat(mobility)
                .isNull();
    }

}