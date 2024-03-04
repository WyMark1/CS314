package com.tco.misc;

import java.lang.Math;

public class cosines implements GreatCircleDistance {

    public cosines() {}
    
    public Long between(GeographicCoordinate geoCord1, GeographicCoordinate geoCord2, double radius) {
        Double phi1 = geoCord1.latRadians();
        Double phi2 = geoCord2.latRadians();
        Double deltaLambda = geoCord2.lonRadians() - geoCord1.lonRadians();
        Double sin = Math.sin(phi1) * Math.sin(phi2);
        Double cos = Math.cos(phi1) * Math.cos(phi2) * Math.cos(deltaLambda);
        Double result = Math.acos(sin + cos);
        result *= radius;
        Long ret = Math.round(result);
        return ret;
    }

}