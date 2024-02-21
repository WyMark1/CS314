package com.tco.requests;
import com.tco.misc.GeographicCoordinate;
import java.util.LinkedHashMap;
import java.lang.Math;

public class Place extends LinkedHashMap<String, String> implements GeographicCoordinate {
    public double lat;
    public double lon;

    Place (String lat, String lon) {

    }
    
    public Place (double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Place () {}
    
    public Double latRadians() { 
        if (Math.abs(lat) <= Math.PI) {
            return lat;
        }
        return Math.toRadians(lat); 
    }

    public Double lonRadians() { 
        if (Math.abs(lon) <= Math.PI) {
            return lon;
        }
        return Math.toRadians(lon); 
    }
}