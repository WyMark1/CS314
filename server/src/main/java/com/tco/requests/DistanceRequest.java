package com.tco.requests;

import java.util.List;
import com.tco.misc.GreatCircleDistance;
import com.tco.misc.GeographicCoordinate;

public class DistanceRequest extends Request {

    private Places places; 
    private Distances distances; 
    private double earthRadius; 
    private String formula; 

    // Constructor
    public DistanceRequest(String requestType, Places places, double earthRadius, String formula) {
        super(requestType);
        this.places = places;
        this.earthRadius = earthRadius;
        this.formula = formula;
        this.distances = new Distances(); 
    }

    // Method to build the response for the distance request
    @Override
    public void buildResponse() {
        // Implementation will be added here
    }

    // Additional getters and setters for the instance variables can be added as needed
}
