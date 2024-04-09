package com.tco.misc;
import java.lang.Math;

public class haversine implements GreatCircleDistance {

    public haversine() {}

    public Long between(GeographicCoordinate geoCord1, GeographicCoordinate geoCord2, double radius) {

        if (radius < 1.0) {
            throw new IllegalArgumentException("Radius must be greater than or equal to 1");
        }

        if (geoCord1 == null || geoCord2 == null) {
            throw new IllegalArgumentException("Geographic coordinates cannot be null.");
        }

        Double phi1 = geoCord1.latRadians();
        Double phi2 = geoCord2.latRadians();
        Double deltaPhi = phi2 - phi1;
        Double deltaLambda = geoCord2.lonRadians() - geoCord1.lonRadians();
        Double sinPhi = Math.pow(Math.sin(deltaPhi / 2), 2);
        Double sinLamba = Math.pow(Math.sin(deltaLambda / 2), 2);
        Double cos = Math.pow(Math.cos((phi1 + phi2) / 2), 2);
        Double result = 2 * Math.asin(Math.sqrt(sinPhi + (cos - sinPhi) * sinLamba));
        result *= radius;
        Long ret = Math.round(result);
        return ret;
    }
}