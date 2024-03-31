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

    public DistanceRequest(Places places, double earthRadius, String formula) {
        if(places == null) {
            throw new IllegalArgumentException("Places can not be null.");
        }
        else { 
            this.places = places;
            this.earthRadius = earthRadius;
            this.formula = formula;
            this.distances = new Distances();
        }
    }

    private void addDistance(String formula) {
        CalculatorFactory calcFac = new CalculatorFactory();
        GreatCircleDistance formulaType = calcFac.get(formula);
        
        for (int i = 0; i < places.size() - 1; i++) {
            distances.add(formulaType.between(places.get(i), places.get(i+1), earthRadius)); 
        }

        distances.add(formulaType.between(places.get(places.size()-1), places.get(0), earthRadius));
    }

    private void buildDistanceList(String formula) {
        if (places.size() >= 2) {
            addDistance(formula);
        }
        else if (places.size() == 1) {
            this.distances.add(0L);
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
        buildDistanceList(formula);
        log.trace("buildResponse -> {}", this);
    }
}