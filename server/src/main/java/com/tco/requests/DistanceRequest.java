package com.tco.requests;

import java.util.List;
import com.google.gson.Gson;
import java.util.Map;
import com.tco.misc.*;
import com.tco.requests.Place;
import com.tco.requests.Places;
import com.tco.requests.Distances;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistanceRequest extends Request {

    private Places places; 
    private Distances distances; 
    private double earthRadius; 
    private String formula; 
    private static final transient Logger log = LoggerFactory.getLogger(DistanceRequest.class);

    // Constructor
    public DistanceRequest(Places places, double earthRadius, String formula) {
        this.places = places;
        this.earthRadius = earthRadius;
        this.formula = formula;
        this.distances = new Distances(); // Initializes the distances collection
    }

    private void addHaversineDistance() {
        haversine haver = new haversine();
        for (int i = 0; i < places.size() - 1; i++) {
            distances.add(haver.between(places.get(i), places.get(i+1), earthRadius)); 
        }
        distances.add(haver.between(places.get(places.size()-1), places.get(0), earthRadius));
    }

    private void addCosinesDistance() {
        cosines cos = new cosines();
        for (int i = 0; i < places.size() - 1; i++) {
                distances.add(cos.between(places.get(i), places.get(i+1), earthRadius)); 
        }
        distances.add(cos.between(places.get(places.size()-1), places.get(0), earthRadius));
    }

    private void addVincentyDistance() {
        vincenty vin = new vincenty();
        for (int i = 0; i < places.size() - 1; i++) {
            distances.add(vin.between(places.get(i), places.get(i+1), earthRadius)); 
        }
        distances.add(vin.between(places.get(places.size()-1), places.get(0), earthRadius));
    }

    private void buildDistanceList() {
        if (places.size() >= 2) {
            if (formula == null || formula.toLowerCase().equals("vincenty")) { 
                addVincentyDistance();
            }
            else if (formula.toLowerCase().equals("haversine")) {
                addHaversineDistance();
            }
            else if (formula.toLowerCase().equals("cosines")) {
                addCosinesDistance();
            } 

        }
        else if (places.size() == 1) {
            this.distances.add(0L);
        } else {
            
        }
    }

    public Distances getDistanceList() {
        return distances;
    }

    @Override
    public void buildResponse() {
        this.requestType = "distances";
        this.distances = new Distances();
        this.earthRadius = earthRadius;
        if (formula != null) {
            this.formula = formula;
        }
        this.places = places;
        buildDistanceList();
        log.trace("buildResponse -> {}", this);
    }

}