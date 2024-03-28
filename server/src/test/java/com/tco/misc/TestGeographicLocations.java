package com.tco.misc;

import com.tco.misc.GeographicLocations;
import com.tco.requests.Places;
import com.tco.requests.Place;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGeographicLocations {
    @Test
    @DisplayName("wymark: Testing base case")
    public void TestBaseCase() {
        GeographicLocations geoloc = new GeographicLocations();
        Place place = new Place(0.0, 0.0); 
        int distance = 0;
        double eathRadius = 0.0;
        String formula = null;
        int limit = 0;
        Places result = new Places();
        assertEquals(result, geoloc.near(place,distance,eathRadius,formula,limit));
    }
}