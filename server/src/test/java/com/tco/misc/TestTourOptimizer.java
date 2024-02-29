package com.tco.misc;

import com.tco.requests.Places;
import com.tco.requests.Place;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTourOptimizer {
    
    @Test
    @DisplayName("wymark: Testing construct returns an empty places list when given an empty list")
    public void TestNoPlaces() {
        //NoOpt optimizer = new NoOpt(); Uncomment this and the assert Once NoOPt exists
        Places places = new Places();
        Double radius = 0.0;
        String formula = "";
        Double response = 0.0;
        //assertEquals(places, optimizer.construct(places, radius, formula, response));
    }

}