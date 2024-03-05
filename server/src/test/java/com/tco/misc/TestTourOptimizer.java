package com.tco.misc;

import com.tco.requests.Places;
import com.tco.requests.Place;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertEquals(result, optimizer.construct(places, radius, formula, response));
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
        assertEquals(result, optimizer.construct(places, radius, formula, response));
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
        result.add(place2);
        result.add(place1);
        result.add(place3);
        result.add(place4);
        Double radius = 3958.8;
        String formula = "vincenty";
        Double response = 0.1;
        assertEquals(result, optimizer.construct(places, radius, formula, response));
    }
}