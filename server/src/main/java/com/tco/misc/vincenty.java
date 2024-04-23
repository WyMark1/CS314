package com.tco.misc;
import java.lang.Math;

public class vincenty implements GreatCircleDistance {
    public vincenty () {}
    
    public Long between(GeographicCoordinate geoCord1, GeographicCoordinate geoCord2, double radius){

        if (radius < 1.0) {
            throw new IllegalArgumentException("Radius must be greater than or equal to 1");
        }

        if (geoCord1 == null || geoCord2 == null) {
            throw new IllegalArgumentException("Geographic coordinates cannot be null.");
        }
        
        Double phi1 = geoCord1.latRadians();
        Double phi2 = geoCord2.latRadians();
        Double deltaLambda = geoCord2.lonRadians()-geoCord1.lonRadians();
        Double val1 = Math.pow(((Math.cos(phi2)) * (Math.sin(deltaLambda))),2) + Math.pow((Math.cos(phi1)*Math.sin(phi2)-Math.sin(phi1)*Math.cos(phi2)*Math.cos(deltaLambda)),2);
        val1 = Math.sqrt(val1);
        Double val2 = Math.sin(phi1)*Math.sin(phi2) + Math.cos(phi1)*Math.cos(phi2)*Math.cos(deltaLambda);
        Double result = Math.atan2(val1,val2);
        result*=radius;
        Long ret = Math.round(result);
        return ret;
    }
    
}
