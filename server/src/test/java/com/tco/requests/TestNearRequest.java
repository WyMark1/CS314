package com.tco.requests;
import com.tco.requests.NearRequest;
import com.tco.requests.Places;
import com.tco.requests.Places;
import com.tco.requests.Distances;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestNearRequest {

    @Test
    @DisplayName("wymark: Testing no places given if there are none.")
    public void TestNoPlace() {
        Place place = new Place(0.0, 0.0);
        Integer distance = 1;
        double earthRadius = 3959;
        String formula = null;
        Integer limit = 0;
        NearRequest request = new NearRequest(place, distance, earthRadius, formula, limit);
        try {
            request.buildResponse();
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
        assertEquals(request.getPlaces().size(), 0);
    }

    @Test
    @DisplayName("wymark: Testing no distances given if there are none.")
    public void TestNoDistance() {
        Place place = new Place(0.0, 0.0);
        Integer distance = 1;
        double earthRadius = 3959;
        String formula = null;
        Integer limit = 0;
        Distances expected = new Distances();
        NearRequest request = new NearRequest(place, distance, earthRadius, formula, limit);
        try {
            request.buildResponse();
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
        assertEquals(request.getDistances(), expected);
    }

    @Test
    @DisplayName("wymark: Null formula is still null")
    public void TestNullFormula() {
        Place place = new Place(0.0, 0.0);
        Integer distance = 1;
        double earthRadius = 3959;
        String formula = null;
        Integer limit = 1;
        NearRequest request = new NearRequest(place, distance, earthRadius, formula, limit);
        try {
            request.buildResponse();
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
        assertEquals(request.getFormula(), formula);
    }

    @Test
    @DisplayName("hca1197: testing NearRequest")
    public void TestNearRequestLimit3() {
        try {
            int distance = 5;
            double earthRadius = 3959.0;
            String formula = "vincenty";
            int limit = 3;
            Distances expectedDistances = new Distances();
            expectedDistances.add(1L);
            expectedDistances.add(1L);
            expectedDistances.add(2L);
            Place place1 = new Place();
            Place place2 = new Place();
            Place place3 = new Place();
            Place location = new Place();
            location.put("name", "Detroit Institute of Arts");
            location.put("latitude", "42.36254");
            location.put("longitude", "-83.06492");
            place1.put("id", "5MI0");
            place1.put("name", "Detroit Medical Center Heliport");
            place1.put("municipality", "Detroit");
            place1.put("region", "Michigan");
            place1.put("country", "United States");
            place1.put("latitude", "42.356998443603516");
            place1.put("longitude", "-83.05770111083984");
            place1.put("altitude", "630");
            place1.put("type", "heliport");
            place2.put("id", "0MI9");
            place2.put("name", "Henry Ford Hospital Heliport");
            place2.put("municipality", "Detroit");
            place2.put("region", "Michigan");
            place2.put("country", "United States");
            place2.put("latitude", "42.36750030517578");
            place2.put("longitude", "-83.08439636230469");
            place2.put("altitude", "633");
            place2.put("type", "heliport");
            place3.put("id", "MI74");
            place3.put("name", "Wdiv-Tv Channel 4 Heliport");
            place3.put("municipality", "Detroit");
            place3.put("region", "Michigan");
            place3.put("country", "United States");
            place3.put("latitude", "42.32979965209961");
            place3.put("longitude", "-83.05349731445312");
            place3.put("altitude", "670");
            place3.put("type", "heliport");
            NearRequest NearRequest = new NearRequest(location, distance, earthRadius, formula, limit);
            NearRequest.buildResponse();
            Places expected = new Places();
            expected.add(place1);
            expected.add(place2);
            expected.add(place3);
            assertEquals(expected, NearRequest.getPlaces());
            assertEquals(expectedDistances, NearRequest.getDistances());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
