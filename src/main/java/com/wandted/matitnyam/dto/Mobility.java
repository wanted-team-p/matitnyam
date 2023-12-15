package com.wandted.matitnyam.dto;

public enum Mobility {

    TRANSPORTATION(5.0),
    WALK(1.0);

    public final Double range;

    Mobility(Double range) {
        this.range = range;
    }

    public static Mobility parseMobility(String mobilityAsString) {
        for (Mobility mobility : Mobility.values()) {
            if (mobility.name().equalsIgnoreCase(mobilityAsString)) {
                return mobility;
            }
        }
        return null;
    }

}
