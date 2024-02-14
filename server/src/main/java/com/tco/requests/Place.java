package com.tco.requests;
import com.tco.misc.GeographicCoordinate;
import java.util.LinkedHashMap;

class Place extends LinkedHashMap<String, String> implements GeographicCoordinate {

    Place (String lat, String lon) {

    }
    Place (double lat, double lon){
        this.lat = latRadians();
        this.lon = lonRadians();
    }

    public Double latRadians() { return 0.0; }
    public Double lonRadians() { return 0.0; }
}