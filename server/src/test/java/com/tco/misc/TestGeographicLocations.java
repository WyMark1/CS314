package com.tco.misc;

import com.tco.misc.*;
import com.tco.requests.Places;
import com.tco.requests.Place;
import com.tco.requests.Distances;
import com.tco.requests.DistanceRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;

public class TestGeographicLocations {

    @Test
    @DisplayName("wymark: Testing base case")
    public void TestBaseCase() {
        GeographicLocations geoloc = new GeographicLocations();
        Place place = new Place(0.0, 0.0); 
        int distance = 1;
        double eathRadius = 1.0;
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
        double earthRadius = 0.0;
        String formula = null;
        GeographicLocations locations = new GeographicLocations();
        Distances distances = locations.distances(place, places, earthRadius, formula);
        assertNotNull(distances);
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
        try {
            assertEquals(result, geoloc.find(match,type,where,limit));
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
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

    @Test
    @DisplayName("hca1197:testing near with a place very far from the start location")
    public void TestNearWithDynamicLatAndLon() {
        try {
            GeographicLocations geoloc = new GeographicLocations();
            int distance = 400;
            double earthRadius = 3959.0;
            String formula = "vincenty";
            int limit = 1;
            Place place1 = new Place();
            Place location = new Place();
            Places result = new Places();
            location.put("name", "NUll bouy");
            location.put("latitude", "0.0");
            location.put("longitude", "0.0");
            place1.put("id", "DGAA");
            place1.put("name", "Kotoka International Airport");
            place1.put("municipality", "Accra");
            place1.put("region", "Greater Accra Region");
            place1.put("country", "Ghana");
            place1.put("latitude", "5.605189800262451");
            place1.put("longitude", "-0.16678600013256073");
            place1.put("altitude", "205");
            place1.put("type", "large_airport");
            result = geoloc.near(location, distance, earthRadius, formula, limit);
            Places expected = new Places();
            expected.add(place1);
            assertEquals(expected, result);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("mstencel9: Testing distances with 3 locations")
    public void TestDistances() {
        try {
            GeographicLocations geoloc = new GeographicLocations();
            CalculatorFactory calcFac = new CalculatorFactory();
            Place location1 = new Place(1.0, 1.0);
            Place location2 = new Place(2.0, 2.0);
            Place location3 = new Place(3.0, 3.0);
            Place place = new Place(0.0, 0.0);
            Places places = new Places();
            double earthRadius = 3959.0;
            String formula = "vincenty";
            GreatCircleDistance formulaType = calcFac.get(formula);
            places.add(location1);
            places.add(location2);
            places.add(location3);
            Distances distancesList = new Distances();
            distancesList.add(formulaType.between(place, places.get(0), earthRadius));
            distancesList.add(formulaType.between(place, places.get(1), earthRadius));
            distancesList.add(formulaType.between(place, places.get(2), earthRadius));
            Distances result = geoloc.distances(place, places, earthRadius, formula);
            assertEquals(distancesList, result);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("mstencel: Testing sorting list to ensure distances over limit are removed and list is sorted")
    public void TestRemoveExtraDistances() {
        GeographicLocations geoloc = new GeographicLocations();
        Distances distanceList = new Distances();
        Distances correctDistances = new Distances();
        Place location1 = new Place(1.0, 1.0);
        Place location2 = new Place(2.0, 2.0);
        Place location3 = new Place(3.0, 3.0);
        Place place = new Place(0.0, 0.0);
        Places places = new Places();
        places.addAll(Arrays.asList(location1, location2, location3, place));
        int distance = 4;
        distanceList.addAll(Arrays.asList(1L, 2L, 3L, 4L));
        correctDistances.addAll(Arrays.asList(1L, 2L, 3L));
        geoloc.removeExtraDistances(distanceList, places, 4);
        assertEquals(correctDistances, distanceList);
    }

    @Test
    @DisplayName("mstencel: Testing near to ensure Places is sorted from least to greatest distance")
    public void TestNearSort() {
        try {
            GeographicLocations geoloc = new GeographicLocations();
            int distance1 = 2;
            int distance2 = 5;
            double earthRadius = 3959.0;
            String formula = "vincenty";
            int limit = 3;
            Place location = new Place();
            Places result1 = new Places();
            Places result2 = new Places();
            location.put("name", "Detroit Institute of Arts"); // Using Austin's testing with limit of 3, I knew that distance 5 allowed 3 places to be in bounds, whereas 2 places wouldn't be.
            location.put("latitude", "42.36254");
            location.put("longitude", "-83.06492");
            int expectedSize1 = 2;
            int expectedSize2 = 3;
            result1 = geoloc.near(location, distance1, earthRadius, formula, limit); // Using Austin's tests,
            result2 = geoloc.near(location, distance2, earthRadius, formula, limit);
            Places expected = new Places();
            assertEquals(expectedSize1, result1.size());
            assertEquals(expectedSize2, result2.size());

        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test 
    @DisplayName("wymark: Testing find with type only")
    public void TestFindWithType() {
        GeographicLocations geoloc = new GeographicLocations();
        String where = "";
        String match = "hello";
        String type = "airport";
        int limit = 3;
        Places result = new Places();
        Place place1 = new Place();
        place1.put("id", "KS70");
        place1.put("name", "Othello Municipal Airport");
        place1.put("municipality", "Othello");
        place1.put("region", "Washington");
        place1.put("country", "United States");
        place1.put("latitude", "46.79489899");
        place1.put("longitude", "-119.0790024");
        place1.put("altitude", "1139");
        place1.put("type", "small_airport");
        result.add(place1);
        try {
            assertEquals(result, geoloc.find(match,type,where,limit));
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test 
    @DisplayName("wymark: Testing find with type only but with a match that has more than limit")
    public void TestFindWithTypeMoreThanLimit() {
        GeographicLocations geoloc = new GeographicLocations();
        String where = "";
        String match = "a";
        String type = "balloonport";
        int limit = 3;
        Places result = new Places();
        
        Place place1 = new Place();
        place1.put("id", "12JY");
        place1.put("name", "Clinton Elks Lodge Balloonport");
        place1.put("municipality", "Pittstown");
        place1.put("region", "New Jersey");
        place1.put("country", "United States");
        place1.put("latitude", "40.60419845581055");
        place1.put("longitude", "-74.9207992553711");
        place1.put("altitude", "37");
        place1.put("type", "balloonport");
        result.add(place1);

        Place place2 = new Place();
        place2.put("id", "13M");
        place2.put("name", "Aeronut Park Balloonport");
        place2.put("municipality", "Howell");
        place2.put("region", "Michigan");
        place2.put("country", "United States");
        place2.put("latitude", "42.60419845581055");
        place2.put("longitude", "-83.85859680175781");
        place2.put("altitude", "980");
        place2.put("type", "balloonport");
        result.add(place2);

        Place place3 = new Place();
        place3.put("id", "28NC");
        place3.put("name", "Balloonport of Greensboro Balloonport");
        place3.put("municipality", "Greensboro");
        place3.put("region", "North Carolina");
        place3.put("country", "United States");
        place3.put("latitude", "35.9557991027832");
        place3.put("longitude", "-79.81890106201172");
        place3.put("altitude", "800");
        place3.put("type", "balloonport");
        result.add(place3);
        try {
            assertEquals(result, geoloc.find(match,type,where,limit));
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}

