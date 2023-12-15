package com.wandted.matitnyam.domain;

import java.beans.ConstructorProperties;
import lombok.Builder;

public record Coordinates(Double latitudeInDegrees, Double longitudeInDegrees) {

    @Builder
    @ConstructorProperties({"latitude", "longitude"})
    public Coordinates {
    }

    public static double calculateDistance(Coordinates source, Coordinates destination) {
        double radiusOfEarthInKiloMeter = 6371;
        double sourceLatitudeInRadians = Math.toRadians(source.latitudeInDegrees);
        double sourceLongitudeInRadians = Math.toRadians(source.longitudeInDegrees);
        double destinationLatitudeInRadians = Math.toRadians(destination.latitudeInDegrees);
        double destinationLongitudeInRadians = Math.toRadians(destination.longitudeInDegrees);

        double xCoordinateOfSource = Math.cos(sourceLatitudeInRadians) * Math.cos(sourceLongitudeInRadians);
        double yCoordinateOfSource = Math.cos(sourceLatitudeInRadians) * Math.sin(sourceLongitudeInRadians);
        double zCoordinateOfSource = Math.sin(sourceLatitudeInRadians);

        double xCoordinateOfDestination =
                Math.cos(destinationLatitudeInRadians) * Math.cos(destinationLongitudeInRadians);
        double yCoordinateOfDestination =
                Math.cos(destinationLatitudeInRadians) * Math.sin(destinationLongitudeInRadians);
        double zCoordinateOfDestination = Math.sin(destinationLatitudeInRadians);

        return radiusOfEarthInKiloMeter * Math.acos(
                xCoordinateOfSource * xCoordinateOfDestination + yCoordinateOfSource * yCoordinateOfDestination
                        + zCoordinateOfSource * zCoordinateOfDestination);
    }

    private static void validateLatitude(String latitudeInString) {
        double latitude = Double.parseDouble(latitudeInString);
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("위도는 -90도 이상 90도 이하의 숫자여야 합니다.");
        }
    }

    private static void validateLongitude(String longitudeInString) {
        double longitude = Double.parseDouble(longitudeInString);
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("경도는 -180도 이상 180도 이하의 숫자여야 합니다.");
        }
    }

}
