import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.tco.requests.*;
import com.tco.misc.*;
import java.util.ArrayList;
import java.util.HashMap;

public class sendSQL {
    
    static class Place extends HashMap<String, String> {}

    static class Places extends ArrayList<Place> {}

    public static ResultSet performQuery(String sql) throws Exception {
        try (
            Connection conn = DriverManager.getConnection(Credential.URL, Credential.USER, Credential.PASSWORD);
            Statement query = conn.createStatement();
        ) {
            return query.executeQuery(sql);
        } catch (Exception e) {
            throw new Exception("Error performing query: " + e.getMessage());
        }
    }

    public static Places places(String sql) throws Exception {
        ResultSet results = performQuery(sql);
        Places places = new Places();
        String[] columns = {"id", "name", "municipality", "iso_region", "iso_country", "latitude", "longitude", "altitude", "type"}; // Adjust columns as necessary
        int count = 0;
        while (results.next()) {
            Place place = new Place();
            for (String column : columns) {
                place.put(column, results.getString(column));
            }
            place.put("index", String.format("%d", ++count));
            places.add(place);
        }
        return places;
    }

    static class Credential {
        final static String USER = "your_user_here";
        final static String PASSWORD = "your_password_here";
        final static String URL = "jdbc:mariadb://your_database_url_here";
    }

}