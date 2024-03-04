package com.tco.requests;

import com.tco.requests.DistanceRequest;
import com.tco.requests.Place;
import com.tco.requests.Places;
import com.tco.requests.Distances;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.Math;
import java.util.Collections; 

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDistanceRequest {

    @Test 
    @DisplayName("mstencel: Testing distances with 0 places to ensure size is 0.")
    public void TestZeroPlaces() {
        Places places = new Places();
        Double radius = 31415.92;
        Long result = 0L;
        String formula = "vincenty";
        DistanceRequest disRequest = new DistanceRequest(places, radius, formula);
        disRequest.buildResponse();
        assertEquals(result, (disRequest.getDistanceList()).size());
    }
    
    @Test 
    @DisplayName("mstencel: Testing distances with 1 places to ensure distance value is 0.")
    public void TestOnePlaces() {
        Place location1 = new Place(0.0, 0.0);
        Places places = new Places();
        places.add(location1);
        Double radius = 31415.92;
        Long result = 0L;
        String formula = "vincenty";
        DistanceRequest disRequest = new DistanceRequest(places, radius, formula);
        disRequest.buildResponse();
        assertEquals(1, (disRequest.getDistanceList()).size());
        assertEquals(result, (disRequest.getDistanceList()).get(0));

    }

    @Test 
    @DisplayName("mstencel: Testing distances with 2 places to ensure correct size.")
    public void TestTwoPlaces() {
        Place location1 = new Place(0.0, 0.0);
        Place location2 = new Place(50.0, -50.0);        
        Places places = new Places();
        places.add(location1);
        places.add(location2);
        Double radius = 31415.92;
        int result = 2;
        String formula = "vincenty";
        DistanceRequest disRequest = new DistanceRequest(places, radius, formula);
        disRequest.buildResponse();
        assertEquals(result, disRequest.getDistanceList().size());
    }

    @Test 
    @DisplayName("mstencel: Testing distances with 3 places to ensure correct size")
    public void TestThreePlaces() {
        Place location1 = new Place(0.0, 0.0);
        Place location2 = new Place(50.0, -50.0);
        Place location3 = new Place(20.0, 30.0);
        Places places = new Places();
        places.add(location1);
        places.add(location2);
        places.add(location3);
        Double radius = 31415.92;
        int result = 3; 
        String formula = "vincenty";
        DistanceRequest disRequest = new DistanceRequest(places, radius, formula);
        disRequest.buildResponse();
        assertEquals(result, disRequest.getDistanceList().size());
    }

    @Test 
    @DisplayName("mstencel: Testing 3 distances with haversine to ensure accuracy")
    public void TestTwoPlacesHaversine() {
        Place location1 = new Place(70.25, 20.75);
        Place location2 = new Place(120.50, 45.00);
        Place location3 = new Place(20.0, 30.0);
        Double radius = 637182.00;
        Places places = new Places();
        places.add(location1);
        places.add(location2);
        places.add(location3);
        Long result1 = 546179L; 
        Long result2 = 1107137L; 
        String formula = "haversine";
        DistanceRequest disRequest = new DistanceRequest(places, radius, formula);
        disRequest.buildResponse();
        assertEquals(result1, disRequest.getDistanceList().get(0)); // place 1 - place 2
        assertEquals(result2, disRequest.getDistanceList().get(1)); // place 2 - place 3
    }

    @Test
    @DisplayName("wymark: testing with real test data and cosines formula")
    public void testUsingCosines(){
        Place place1 = new Place(47.591171, -53.542164);
        Place place2 = new Place(-37.067786, 12.311201);
        Place place3 = new Place(-75.087191, 122.934432);
        Place place4 = new Place(50.745172, 6.270106);
        Double radius = 31415.92;
        Long[] distancesArr = {56475L, 32530L, 79625L, 20922L};
        Distances distances = new Distances();
        Collections.addAll(distances, distancesArr);
        Place[] placesArr = {place1, place2, place3, place4};
        Places places = new Places();
        Collections.addAll(places, placesArr);
        String formula = "cosines";
        DistanceRequest disRequest = new DistanceRequest(places, radius, formula);
        disRequest.buildResponse();
        assertEquals(distances, disRequest.getDistanceList());
    }
}