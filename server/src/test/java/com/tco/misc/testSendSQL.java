import com.tco.misc.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
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
}