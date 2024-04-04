import com.tco.misc.*;
import java.util.*;
import com.tco.requests.Place;
import com.tco.requests.Places;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import static org.junit.jupiter.api.Assertions.*;


public class testSendSQL {

    @Test
    @DisplayName("hca1197: Outline of sendSQL test to add when implemente.")
    public void testPerformQuery() {
        /* try {

            // A test should be added with an assert to check if performQuery is retrieving the right information.
            
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        } */
    }

    @Test
    @DisplayName("brownbp: Testing base case for found")
    public void TestFound() {
        sendSQL send = new sendSQL();
        Place place = new Place(0.0, 0.0); 
        int distance = 0;
        double eathRadius = 0.0;
        String formula = null;
        int limit = 0;
        assertEquals(0, send.found());
    }

    @Test
    @DisplayName("mstencel: Testing performQuery SQL") 
    public void testingPerformQuery() {
        String sql = "select id from world limit 1;";
        sendSQL send = new sendSQL();
        try {
            ResultSet result = send.performQuery(sql);
            System.out.println(result.getString("id"));
            assertTrue(result.getString("id").equals("00A"));
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }   
    }

    @Test
    @DisplayName("wmark: Returns a places array of 1")
    public void testPlaces() {
        try {
            sendSQL send = new sendSQL();
            String select = "SELECT world.id, world.name, world.municipality, region.name, country.name, world.latitude, world.longitude, world.altitude, world.type ";
            String from = "FROM continent INNER JOIN country ON continent.id = country.continent INNER JOIN region ON country.id = region.iso_country INNER JOIN world ON region.id = world.iso_region ";
            String where = "WHERE world.latitude BETWEEN 40 - 1 AND 40 + 1 AND world.longitude BETWEEN -74 - 1 AND -74 + 1 LIMIT 3;";
            String sql = select + from + where;
            Places result = send.places(sql);
            Places expected = new Places();
            Place place1 = new Place();
            place1.put("name", "Total Rf Heliport");
            place1.put("municipality", "Bensalem");
            place1.put("state", "Pennsylvania");
            place1.put("country", "United States");
            place1.put("latitude", "40.07080078125");
            place1.put("longitude", "-74.93360137939453");
            place1.put("altitude", "11");
            place1.put("type", "heliport");
            assertEquals(result, expected);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

}