package com.tco.misc;

import com.tco.requests.Places;
import com.tco.requests.Place;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.Arrays;

public class TestTourOptimizer {
    
    @Test
    @DisplayName("wymark: Testing construct returns an empty places list when given an empty list")
    public void TestNoPlaces() {
        NoOptimizer optimizer = new NoOptimizer();
        Places places = new Places();
        Places result = new Places();
        Double radius = 1.0;
        String formula = "vincenty";
        Double response = 0.1;
        try {
        assertEquals(result, optimizer.construct(places, radius, formula, response));
    } catch(Exception e) {
        fail("Exception occurred: " + e.getMessage());
    }
    }

    @Test
    @DisplayName("wymark: Testing construct returns the same list when given an a list with two places")
    public void TestTwoPlaces() {
        Place place1 = new Place(90.0, 100.0);
        Place place2 = new Place(-90.0, -100.0);
        NoOptimizer optimizer = new NoOptimizer();
        Places places = new Places();
        Places result = new Places();
        places.add(place1);
        places.add(place2);
        result.add(place1);
        result.add(place2);
        Double radius = 1.0;
        String formula = "vincenty";
        Double response = 0.1;
        try {
        assertEquals(result, optimizer.construct(places, radius, formula, response));
    } catch(Exception e) {
        fail("Exception occurred: " + e.getMessage());
    }
    }

    @Test
    @DisplayName("wymark: Testing construct returns an optimized list when given an a list with four places")
    public void TestFourPlaces() {
        Place place1 = new Place(45.0, 100.0);
        Place place2 = new Place(90.0, 100.0);
        Place place3 = new Place(-12.5, 100.0);
        Place place4 = new Place(-45.0, -100.0);
        NoOptimizer optimizer = new NoOptimizer();
        Places places = new Places();
        Places result = new Places();
        places.add(place1);
        places.add(place2);
        places.add(place3);
        places.add(place4);
        result.add(place1);
        result.add(place3);
        result.add(place4);
        result.add(place2);
        Double radius = 3958.8;
        String formula = "vincenty";
        Double response = 0.1;
        try {
        assertEquals(result, optimizer.construct(places, radius, formula, response));
    } catch(Exception e) {
        fail("Exception occurred: " + e.getMessage());
    }
    }

    @Test
    @DisplayName("hca1197: Testing construct throws BadRequestException for radius less than 1.0 or response less than 0.0")
    public void TestRadiusOrResponseLessThanMinimum() {
        NoOptimizer optimizer = new NoOptimizer();
        Places places = new Places();
        Double radius = 0.5;
        String formula = "vincenty";
        Double response = -0.1; 
        try {
            optimizer.construct(places, radius, formula, response);
            fail("Expected BadRequestException, but no exception was thrown");
        } catch (BadRequestException e) {
            assertEquals("Radius must be at least 1 and response must be positive.", e.getMessage());
        }
    }

    @Test
    @DisplayName("hca1197: Testing construct throws BadRequestException for invalid formula")
    public void TestInvalidFormula() {
        NoOptimizer optimizer = new NoOptimizer();
        Places places = new Places();
        Double radius = 1.0;
        String formula = "null"; 
        Double response = 0.1;
        try {
            optimizer.construct(places, radius, formula, response);
            fail("Expected BadRequestException, but no exception was thrown");
        } catch (BadRequestException e) {
            assertEquals("Unsupported formula: " + formula, e.getMessage());
        }
    }
}
