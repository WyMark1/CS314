import com.tco.misc.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import static org.junit.jupiter.api.Assertions.*;
import com.tco.requests.Place;

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
        GeographicLocations geoloc = new GeographicLocations();
        Place place = new Place(0.0, 0.0); 
        int distance = 0;
        double eathRadius = 0.0;
        String formula = null;
        int limit = 0;
        assertEquals(0, geoloc.found());
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
}