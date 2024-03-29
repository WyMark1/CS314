package com.tco.misc;

import com.tco.requests.Place;
import com.tco.requests.Distances;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGeographicLocations {

    @Test
    @DisplayName("hca1197: Simple distances method check.")
    public void testDistancesNull() {
        GeographicLocations locations = new GeographicLocations();
        Distances distances = locations.distances();
        assertNotNull(distances);
}

}
