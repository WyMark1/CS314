package com.tco.misc;

import com.tco.misc.*;
import java.util.*;
import com.tco.requests.Places;
import com.tco.requests.Place;
import com.tco.requests.Distances;
import com.tco.requests.DistanceRequest;
import com.tco.misc.BadRequestException;
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
        try {
        Distances distances = locations.distances(place, places, earthRadius, formula);
        assertNotNull(distances);
        } catch (BadRequestException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("josh1302: Testing base case for find")
    public void TestFind() {
        GeographicLocations geoloc = new GeographicLocations();
        List<String> where = null;
        String match = "";
        List<String> type = null;
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
    @DisplayName("mstencel: Testing sorting list to ensure distances over limit are removed")
    public void TestRemoveExtraAndSortDistances() {
        GeographicLocations geoloc = new GeographicLocations();
        Distances distanceList = new Distances();
        Place location1 = new Place(1.0, 1.0);
        Place location2 = new Place(2.0, 2.0);
        Place location3 = new Place(3.0, 3.0);
        Place place = new Place(0.0, 0.0);
        Places places = new Places();
        Places sortedPlaces = new Places();
        Places correctPlaces = new Places();
        places.addAll(Arrays.asList(location1, location2, location3, place));
        int distance = 3;
        distanceList.addAll(Arrays.asList(1L, 2L, 3L, 4L));
        correctPlaces.addAll(Arrays.asList(location1, location2, location3));
        sortedPlaces = geoloc.removeExtraAndSortDistances(distanceList, places, distance);
        assertEquals(correctPlaces, sortedPlaces);
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
        List<String> where = null;
        String match = "hello";
        List<String> type = new ArrayList<String>();
        type.add("airport");
        type.add("heliport");
        int limit = 3;
        Places result = new Places();
        Place place1 = new Place();
        place1.put("id", "24WA");
        place1.put("name", "Othello Community Hospital Heliport");
        place1.put("municipality", "Othello");
        place1.put("region", "Washington");
        place1.put("country", "United States");
        place1.put("latitude", "46.82609939575195");
        place1.put("longitude", "-119.16899871826172");
        place1.put("altitude", "1038");
        place1.put("type", "heliport");
        result.add(place1);

        Place place2 = new Place();
        place2.put("id", "57WA");
        place2.put("name", "Kent Farms Airport");
        place2.put("municipality", "Othello");
        place2.put("region", "Washington");
        place2.put("country", "United States");
        place2.put("latitude", "46.87350082397461");
        place2.put("longitude", "-119.12999725341797");
        place2.put("altitude", "1155");
        place2.put("type", "small_airport");
        result.add(place2);

        Place place3 = new Place();
        place3.put("id", "6WA4");
        place3.put("name", "Ochoa Field");
        place3.put("municipality", "Othello");
        place3.put("region", "Washington");
        place3.put("country", "United States");
        place3.put("latitude", "46.8628997803");
        place3.put("longitude", "-119.138999939");
        place3.put("altitude", "1149");
        place3.put("type", "small_airport");
        result.add(place3);
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
        List<String> where = new ArrayList<String>();
        where.add("United States");
        where.add("China");
        String match = "a";
        List<String> type = new ArrayList<String>();
        type.add("balloonport");
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
    @Test 
    @DisplayName("josh1302: Testing find with incorrect country")
    public void TestFindWithCOuntryWithNoPlaces() {
        GeographicLocations geoloc = new GeographicLocations();
        List<String> where = new ArrayList<String>();
        where.add("China");
        String match = "a";
        List<String> type = new ArrayList<String>();
        type.add("balloonport");
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
            assertNotEquals(result, geoloc.find(match,type,where,limit));
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("mstencel: Testing distance sorting for near")
        public void testRemoveExtraAndSortDistancesSorting() {
            GeographicLocations geoloc = new GeographicLocations();
            Distances distanceList = new Distances();
            Place location1 = new Place(1.0, 1.0);
            Place location2 = new Place(4.0, 4.0);
            Place location3 = new Place(10.0, 10.0);
            Place place = new Place(0.0, 0.0);
            Places places = new Places();
            places.addAll(Arrays.asList(location3, location1, place, location2));
            distanceList.addAll(Arrays.asList(3L, 1L, 4L, 2L));
            int distance = 5;
            Places sortedPlaces = geoloc.removeExtraAndSortDistances(distanceList, places, distance);
            Place[] expectedPlaces = { location1, location2, location3 };

            for (int i = 0; i < sortedPlaces.size() - 1; i++) {
               assertEquals(expectedPlaces[i], sortedPlaces.get(i));
            }

    }
}

