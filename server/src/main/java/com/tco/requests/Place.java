package com.tco.requests;
import com.tco.misc.GeographicCoordinate;
import java.util.LinkedHashMap;
import java.lang.Math;

public class Place extends LinkedHashMap<String, String> implements GeographicCoordinate {
    
    public Place (Double lat, Double lon) {
        this.put("latitude", lat+"");
        this.put("longitude", lon+"");
    }

    public Place () {}
    
    public Double latRadians() { 
        Double lat = Double.parseDouble(this.get("latitude"));
        return Math.toRadians(lat); 
    }

    public Double lonRadians() {
        Double lon = Double.parseDouble(this.get("longitude"));
        return Math.toRadians(lon); 
    }
}
