package com.tco.misc;

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
    final static String URL = "jdbc:mariadb://localhost:3306/cs314";
    
    public sendSQL() {}
    
    public ResultSet performQuery (String sql) throws BadRequestException {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement query = conn.createStatement();
            ResultSet results = query.executeQuery(sql);
            return results;
        } catch (Exception e){
            BadRequestException BRE = new BadRequestException("Invalid Query preform query: " + e.getMessage(), e);
            throw BRE;
        }
    }
    
    public Places places(String sql) throws BadRequestException {
        try {
            ResultSet results = performQuery(sql);
            String[] cols = {"id", "name", "municipality", "region", "country", "latitude", "longitude", "altitude", "type"};
		    Places places = new Places();
		    while (results.next()) {
		        Place place = new Place();
			for (String col : cols) {
				place.put(col, results.getString(col));
			}

			places.add(place);
		}
		return places;
        } catch (Exception e) {
            BadRequestException BRE = new BadRequestException("Places: " + e.getMessage(), e);
            throw BRE;
        }
    }

    public Integer found(){
        return 0;
    }

}