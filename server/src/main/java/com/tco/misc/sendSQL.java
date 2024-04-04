import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.tco.requests.*;
import com.tco.misc.*;
import java.util.ArrayList;
import java.util.HashMap;

public class sendSQL {
    final static String USER = "cs314-db";
    final static String PASSWORD = "eiK5liet1uej";
    final static String URL = "jdbc:mariadb://localhost:41311/cs314";


    public Integer found(){
        return 0;
    }
    
    public sendSQL() {}
    
    public ResultSet performQuery (String sql) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement query = conn.createStatement();
        ResultSet results = query.executeQuery(sql);
        return results;
}
    public Places places(String sql) throws Exception {
        return null;
    }

}