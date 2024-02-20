package com.tco.requests;

import java.util.List;

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
        super(); // Call the default constructor of Request
        this.places = places;
        this.earthRadius = earthRadius;
        this.formula = formula;
        this.distances = new Distances(); // Initializes the distances collection
    }

    public void addHaversineDistance() {
        haversine haver = new haversine();
        for (int i = 0; i < places.size() - 1; i++) {
            for (int j = i + 1; j < places.size(); j++) {  // Calculates distance from each iteration until the end of the list then moves up. 
            // ex. place 1 - place 2, then place 1 - place 3, then place 2 - place 3. Ensures no repeats and more efficient time.
                distances.add(haver.between(places.get(i), places.get(j), earthRadius)); 
            }
        }
    }

    public void addCosinesDistance() {
        cosines cos = new cosines();
        for (int i = 0; i < places.size() - 1; i++) {
            for (int j = i + 1; j < places.size(); j++) { 
                distances.add(cos.between(places.get(i), places.get(j), earthRadius)); 
            }
        }
    }

    public void addVincentyDistance() {
        vincenty vin = new vincenty();
        for (int i = 0; i < places.size() - 1; i++) {
            for (int j = i + 1; j < places.size(); j++) { 
                distances.add(vin.between(places.get(i), places.get(j), earthRadius)); 
            }
        }
    }

    public void buildDistanceList() {
        if (places.size() >= 2) {
            if (formula.toLowerCase().equals("haversine")) {
                addHaversineDistance();
            }
            else if (formula.toLowerCase().equals("cosines")) {
                addCosinesDistance();
            }
            else { // Assuming default is vincenty, but can change to say if vincenty otherwise.
                addVincentyDistance();
            }
        }
        else {
            distances.add(0L);
        }
    }

    public Distances getDistanceList() {
        return distances;
    }

    @Override
    public void buildResponse() {
        buildDistanceList();
        distances = getDistanceList();
        log.trace("buildResponse -> {}", this);
    }
}
