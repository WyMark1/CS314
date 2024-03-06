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
        OptimizerFactory optimizerFactory = new OptimizerFactory();
        TourOptimizer optimizer = optimizerFactory.get(places.size(), response);
        if (optimizer != null) {
            this.places = optimizer.construct(places, earthRadius, formula, response);
            log.info("Tour has been optimized.");
        } else {
            log.error("No suitable optimizer found for the given criteria.");
        } 
        this.response = response;
        log.trace("buildResponse -> {}", this);
    }
    
}