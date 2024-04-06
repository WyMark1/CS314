package com.tco.misc;

import com.tco.misc.GeographicLocations;
import com.tco.requests.Places;
import com.tco.requests.Place;
import com.tco.requests.Distances;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
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
        try {
            assertEquals(result, geoloc.near(place,distance,eathRadius,formula,limit));
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }



    @Test
    @DisplayName("hca1197: Simple distances method check.")
    public void testDistancesNull() {
        Place place = new Place();
        Places places = new Places();
        GeographicLocations locations = new GeographicLocations();
        Distances distances = locations.distances(place, places);
        assertNotNull(distances);
    }

    @Test
    @DisplayName("josh1302: Testing base case for found")
    public void TestFound() {
        GeographicLocations geoloc = new GeographicLocations();
        Place place = new Place(0.0, 0.0); 
        int distance = 0;
        double eathRadius = 0.0;
        String formula = null;
        int limit = 0;
        assertEquals(0, geoloc.found());
    }
    @Test
    @DisplayName("josh1302: Testing base case for find")
    public void TestFind() {
        GeographicLocations geoloc = new GeographicLocations();
       String where = "";
        String match = "";
        String type = "";
        int limit = 0;
        Places result = new Places();
        assertEquals(result, geoloc.find(match,type,where,limit));
    }

    @Test
    @DisplayName("hca1197:testing near with limit 3")
    public void TestNear() {
        try {
            GeographicLocations geoloc = new GeographicLocations();
            int distance = 5;
            double earthRadius = 3959.0;
            String formula = "vincenty";
            int limit = 3;
            Place place1 = new Place();
            Place place2 = new Place();
            Place place3 = new Place();
            Place location = new Place();
            Places result = new Places();
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
            result = geoloc.near(location, distance, earthRadius, formula, limit);
            Places expected = new Places();
            expected.add(place1);
            expected.add(place2);
            expected.add(place3);
            assertEquals(expected, result);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}

