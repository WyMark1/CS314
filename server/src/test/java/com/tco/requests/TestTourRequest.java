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
import com.google.gson.Gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestTourRequest {

    @Test 
    @DisplayName("hca1197: Initial TourRequest test. Testing compilation and initializing of values.")
    public void testBuildResponseInitialization() {
        Places places = new Places();
        double earthRadius = 3959.0;
        String formula = "vincenty";
        double response = 1.0;
        TourRequest tourRequest = new TourRequest(places, earthRadius, formula, response);
        try{
        tourRequest.buildResponse();
        assertEquals("tour", tourRequest.getRequestType());
        } catch(Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }

    }

    @Test
    @DisplayName("hca1197: Testing tour request with array of 4 places and 0 response.")
    public void testZeroResponse() {
        Place place1 = new Place(33.527745, -86.765053);
        Place place2 = new Place(34.743843, -92.268229);
        Place place3 = new Place(41.958613, -87.817977);
        Place place4 = new Place(-8.645522, 115.1412500);
        Double radius = 31415.92;
        String formula = "haversine";
        Double response = 0.0;
        Place[] placesArr = {place1, place2, place3, place4};
        Places places = new Places();
        Collections.addAll(places, placesArr);
        List<Place> expectedPlaces = Arrays.asList(placesArr);
        TourRequest tourRequest = new TourRequest(places, radius, formula, response);
        try {
        tourRequest.buildResponse();
        assertEquals(expectedPlaces, tourRequest.getPlaces()); // with respone 0, TourRequest should just return the same array.
        } catch(Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }

    }

    @Test
    @DisplayName("hca1197: Testing tour request with array less than 4.")
    public void testSizeThreeArray() {
        Place place1 = new Place(33.527745, -86.765053);
        Place place2 = new Place(34.743843, -92.268229);
        Place place3 = new Place(41.958613, -87.817977);
        Double radius = 31415.92;
        String formula = "vincenty";
        Double response = 1.0;
        Place[] placesArr = {place1, place2, place3};
        Places places = new Places();
        Collections.addAll(places, placesArr);
        List<Place> expectedPlaces = Arrays.asList(placesArr);
        TourRequest tourRequest = new TourRequest(places, radius, formula, response);
        try {
        tourRequest.buildResponse();
        assertEquals(expectedPlaces, tourRequest.getPlaces()); // with array size less than 4, TourRequest should just return the same array.
        } catch(Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }

    }

    @Test
    @DisplayName("hca1197: Testing list of 4 places is optimized.")
    public void testFourOptimized() {
        Place place1 = new Place(45.0, 100.0);
        Place place2 = new Place(90.0, 100.0);
        Place place3 = new Place(-12.5, 100.0);
        Place place4 = new Place(-45.0, -100.0);
        Double radius = 3958.8;
        String formula = "vincenty";
        Double response = 0.1;
        Places places = new Places();
        Places expectedPlaces = new Places();
        places.add(place1);
        places.add(place2);
        places.add(place3);
        places.add(place4);
        expectedPlaces.add(place1);
        expectedPlaces.add(place3);
        expectedPlaces.add(place4);
        expectedPlaces.add(place2);
        TourRequest tourRequest = new TourRequest(places, radius, formula, response);
        try {
        tourRequest.buildResponse();
        assertEquals(expectedPlaces, tourRequest.getPlaces());
        } catch(Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }

    }
}