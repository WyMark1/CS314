package com.tco.misc;

import com.tco.requests.Place;
import com.tco.misc.cosines;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.Math;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCosines {

    @Test 
    @DisplayName("wymark: Testing basic cosine distance formula")
    public void TestCosineBasic() {
        Place location1 = new Place(50.50, 30.30);
        Place location2 = new Place(75.75, 122.122);
        Double radius = 31415.92;
        Long result = 23040L;
        cosines cosine = new cosines();
        assertEquals(result, cosine.between(location1, location2, radius));
    }

    @Test 
    @DisplayName("wymark: Testing cosine distance formula with real coordinates")
    public void TestCosineWithRealValues() {
        Place location1 = new Place(-29.31689, 27.48608);
        Place location2 = new Place(48.20335, 16.37262);
        Double radius = 10241024.10;
        Long result = 13970101L;
        cosines cosine = new cosines();
        assertEquals(result, cosine.between(location1, location2, radius));
    }
}
