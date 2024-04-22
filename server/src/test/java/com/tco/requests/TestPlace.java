package com.tco.requests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.Math;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPlace {

    @Test
    @DisplayName("wymark: test latitude degree to radian conversion")
    public void testLatDegreeToRadians() {
        double latitude = 37.2753;
        double longitude = 0.0;
        Place place = new Place(latitude, longitude);
        assertTrue(place.latRadians()==Math.toRadians(latitude));
    }

    @Test
    @DisplayName("wymark: test longitude degree to radian conversion")
    public void testLonDegreeToRadians() {
        double latitude = 0.0;
        double longitude = -107.8801;
        Place place = new Place(latitude, longitude);
        assertTrue(place.lonRadians()==Math.toRadians(longitude));
    }

    @Test
    @DisplayName("wymark: test latitude degree to radian conversion with small degrees")
    public void testLatSmallToRadians() {
        double latitude = 0.6505;
        double longitude = 0.0;
        Place place = new Place(latitude, longitude);
        assertTrue(place.latRadians()==Math.toRadians(latitude));
    }

    @Test
    @DisplayName("wymark: test longitude degree to radian conversion with small degrees")
    public void testLonSmallToRadians() {
        double latitude = 0.0;
        double longitude = 0.6505;
        Place place = new Place(latitude, longitude);
        assertTrue(place.lonRadians()==Math.toRadians(longitude));
    }
}
