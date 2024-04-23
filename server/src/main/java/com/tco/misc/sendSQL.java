package com.tco.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.tco.requests.*;
import com.tco.misc.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class sendSQL {
    final static String USER = "cs314-db";
    final static String PASSWORD = "eiK5liet1uej";
    final static String URL = "jdbc:mariadb://faure.cs.colostate.edu/cs314";
    private Integer resultCount = 0;
    
    public sendSQL() {}
    
    public ResultSet performQuery(String sql) throws BadRequestException {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement query = conn.createStatement();
            ResultSet results = query.executeQuery(sql);
            resultCount = getResult(sql);
            return results;
        } catch (Exception e) {
            BadRequestException BRE = new BadRequestException("Invalid Query preform query: " + e.getMessage(), e);
            throw BRE;
        }
    }
    
    public List<String> getPlacesForWhere() throws BadRequestException {
        try {
            String select = "SELECT DISTINCT country.name AS country ";
            String from = "FROM continent INNER JOIN country ON continent.id = country.continent INNER JOIN region ON country.id = region.iso_country INNER JOIN world ON region.id = world.iso_region ";
            String orderBy = "ORDER BY country.name;";

            ResultSet results = performQuery(select+from+orderBy); 
            List<String> contries = new ArrayList<String>();
    
            while (results.next()) {
                contries.add(results.getString("country"));
            }

            return contries;
        } catch (Exception e) {
            BadRequestException BRE = new BadRequestException("Places: " + e.getMessage(), e);
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

    public Integer found() {
        return resultCount;
    }

    private int getResult(String sql) throws BadRequestException {
        String countSql = sql.replaceAll("LIMIT \\d+", "");
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement query = conn.createStatement();
            ResultSet results = query.executeQuery(countSql);
            int count = 0;
            try {
                results.last();
                count = results.getRow();
                results.beforeFirst();
            } catch (Exception e) {
                BadRequestException BRE = new BadRequestException("Places: " + e.getMessage(), e);
                throw BRE;
            }
            
            return count;
        } catch (Exception e) {
            BadRequestException BRE = new BadRequestException("Count Total Results: " + e.getMessage(), e);
            throw BRE;
        }
    }
    }
