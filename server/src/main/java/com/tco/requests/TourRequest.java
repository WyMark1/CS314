package com.tco.requests;

import java.util.List;
import com.google.gson.Gson;
import java.util.Map;
import com.tco.misc.*;
import com.tco.requests.Place;
import com.tco.requests.Places;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a request to optimize a tour of places according to a specific formula and criteria.
 */
public class TourRequest extends Request {

    private Places places;
    private double earthRadius;
    private String formula;
    private double response;
    private static final transient Logger log = LoggerFactory.getLogger(TourRequest.class);

    /**
     * Constructs a TourRequest with the provided parameters.
     * 
     * @param places the set of places to be included in the tour
     * @param earthRadius the radius of Earth used in distance calculations
     * @param formula the formula to use for optimizing the tour
     * @param response initial response value, potentially used in optimization
     */

    public TourRequest(Places places, double earthRadius, String formula, double response) {
        this.places = places;
        this.earthRadius = earthRadius;
        this.formula = formula; 
        this.response = response;
    }

    public Places getPlaces() {
        return places;
    }

    /**
     * Builds the optimized tour response. Utilizes an optimization strategy based on the specified formula
     * and current response value. Logs the outcome of the optimization process.
     */
    @Override
    public void buildResponse() {
        this.requestType = "tour";
        this.response = response;
        if (formula != null){
            this.formula=formula;
        }
        this.places=places;
        this.earthRadius=earthRadius;
        OptimizerFactory optimizerFactory = new OptimizerFactory();
        TourOptimizer optimizer = optimizerFactory.get(1, response);
        if (optimizer != null) {
            this.places = optimizer.construct(places, earthRadius, formula, response);
            log.info("Tour has been optimized.");
        } else {
            log.error("No suitable optimizer found for the given criteria.");
        } 
        
        log.trace("buildResponse -> {}", this);
    }
    
}