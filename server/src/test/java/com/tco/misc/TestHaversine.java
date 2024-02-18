package com.tco.misc;

import com.tco.requests.Place;
import com.tco.misc.haversine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.lang.Math;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHaversine {

    @Test
    @DisplayName("hca1197: Testing haversine with basic values.")
    public void TestHaversineBasic() {
        Place location1 = new Place(70.25, 20.75);
        Place location2 = new Place(120.50, 45.00);
        Double radius = 637182.00;
        Long result = 546179L;
        haversine Haversine = new haversine();
        assertEquals(result, Haversine.between(location1, location2, radius));
    }

    @Test
    @DisplayName("hca1197: Testing haversine with actual coordinates.")
    public void TestHaversineReal() {
        Place location1 = new Place(42.9634, -85.6681);
        Place location2 = new Place(-34.6037, -58.3816);
        Double radius = 6371008.7714;
        Long result = 9059423L;
        haversine Haversine = new haversine();
        assertEquals(result, Haversine.between(location1, location2, radius));
    }
}