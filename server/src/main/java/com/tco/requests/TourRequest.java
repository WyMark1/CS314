package com.tco.requests;

import java.util.List;
import com.google.gson.Gson;
import java.util.Map;
import com.tco.misc.*;
import com.tco.requests.Place;
import com.tco.requests.Places;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TourRequest extends Request {

    private Places places;
    private double earthRadius;
    private String formula;
    private double response;
    private static final transient Logger log = LoggerFactory.getLogger(TourRequest.class);

    public TourRequest(Places places, double earthRadius, String formula, double response) {
        this.places = places;
        this.earthRadius = earthRadius;
        this.formula = formula; 
        this.response = response;
    }

    @Override
    public void buildResponse() {
        this.requestType = "tour";
        double totalDistance = 0.0;

        for (int i = 0; i < places.size(); i++) {
            Place currentPlace = places.get(i);
            Place nextPlace = places.get((i + 1) % places.size()); 
            totalDistance += calculateDistance(currentPlace, nextPlace);
        }

        this.response = totalDistance; 
    }

    private double calculateDistance(Place a, Place b) {
        double lat1 = a.latRadians();
        double lon1 = a.lonRadians();
        double lat2 = b.latRadians();
        double lon2 = b.lonRadians();
    
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
    
        double radius = this.earthRadius; 
    
        double aVal = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                      Math.cos(lat1) * Math.cos(lat2) *
                      Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(aVal), Math.sqrt(1 - aVal));
        return radius * c;
    }
    
    
}