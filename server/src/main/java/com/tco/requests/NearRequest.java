package com.tco.requests;
import com.tco.requests.Place;
import com.tco.requests.Places;
import com.tco.requests.Distances;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearRequest extends Request{
    private Place place;
    private Integer distance;
    private double earthRadius;
    private String formula;
    private Integer limit;
    private Places places;
    private Distances distances;
    private static final transient Logger log = LoggerFactory.getLogger(NearRequest.class);

    public NearRequest(Place place, Integer distance, double earthRadius, String formula, Integer limit) {
        this.place = place;
        this.distance = distance;
        this.earthRadius = earthRadius;
        this.formula = formula;
        this.limit = limit;
    }
    
    public Places getPlaces() {
        return places;
    }

    @Override
    public void buildResponse() {
        places = new Places(); //replace with GeogrphicLocations.near()
    }
}
