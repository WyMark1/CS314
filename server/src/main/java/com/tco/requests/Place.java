package com.tco.requests;
import com.tco.misc.GeographicCoordinate;
import java.util.LinkedHashMap;

class Place extends LinkedHashMap<String, String> implements GeographicCoordinate {
    public double lat;
    public double lon;

    Place (String lat, String lon) {

    }
    Place (double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }

    public Double latRadians() { return lat; }
    public Double lonRadians() { return 0.0; }
}