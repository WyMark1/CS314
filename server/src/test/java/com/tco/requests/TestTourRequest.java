package com.tco.requests;

import com.tco.requests.TourRequest;
import com.tco.requests.Place;
import com.tco.requests.Places;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.Math;
import java.util.Collections; 
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTourRequest {

    @Test 
    @DisplayName("hca1197: Initial TourRequest test. Testing compilation and initializing of values.")
    public void testBuildResponseInitialization() {
        Places places = new Places();
        double earthRadius = 3959.0;
        String formula = "vincenty";
        double response = 1.0;
        TourRequest tourRequest = new TourRequest(places, earthRadius, formula, response);
        tourRequest.buildResponse();
        assertEquals("tour", tourRequest.getRequestType());
    }

}