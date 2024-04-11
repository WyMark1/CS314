package com.tco.misc;

import com.tco.requests.Places;
import com.tco.requests.Place;
import com.tco.requests.Distances;
import com.tco.misc.sendSQL;
import com.tco.misc.BadRequestException;
import java.lang.Math;
import java.util.*;

public class GeographicLocations {
    private static String select = "SELECT world.id, world.name, world.municipality, region.name AS region, country.name AS country, world.latitude, world.longitude, world.altitude, world.type ";
    private static String from = "FROM continent INNER JOIN country ON continent.id = country.continent INNER JOIN region ON country.id = region.iso_country INNER JOIN world ON region.id = world.iso_region ";
    private Integer found;

    public GeographicLocations() {

    }
    
    public Integer getFound(){
        return found;
    }

    public Places near(Place place, int distance, double earthRadius, String formula, int limit) throws BadRequestException {
        Distances distanceList = new Distances();
        String lat = place.get("latitude");
        String lon = place.get("longitude");
        double latDelta = (distance / earthRadius) * (180 / Math.PI);
        double lonDelta = (distance / earthRadius) * (180 / Math.PI) / Math.cos(place.latRadians());
        String selectNear = select + ", ABS(world.latitude - "+lat+") AS lat_diff,  ABS(world.longitude - "+lon+") AS lon_diff ";
        String where =  "WHERE world.latitude BETWEEN " +lat+ " - "+latDelta+" AND "+lat+" + "+latDelta+"  AND world.longitude BETWEEN " +lon+ " - "+ lonDelta +" AND "+lon+" + " + lonDelta;
        String orderBy = " ORDER BY lat_diff + lon_diff LIMIT "+Integer.toString(limit) +";";
        String sql = selectNear + from + where + orderBy;
        sendSQL s = new sendSQL();
        distanceList = distances(place, s.places(sql), earthRadius, formula);
        removeExtraDistances(distanceList, s.places(sql), distance);
        return s.places(sql);
    }

    public Distances distances(Place place, Places places, double earthRadius, String formula) throws BadRequestException {
        Distances distances = new Distances();
        Places sortedPlaces = new Places();
        CalculatorFactory calcFac = new CalculatorFactory();
        GreatCircleDistance formulaType = calcFac.get(formula);
        for (Place p : places) {
            if (!place.equals(p)) {
                distances.add(formulaType.between(place, p, earthRadius));
            }
        }
        
        return distances;
    }

    public void removeExtraDistances(Distances distances, Places places, long distance) {
        Distances sortedDistances = new Distances();
        Places sortedPlaces = new Places();
        for (int i = 0; i < distances.size(); i++) {
            if (distances.get(i) < distance) {
                sortedDistances.add(distances.get(i));
                sortedPlaces.add(places.get(i));
            }
        }
        
        places.clear();
        distances.clear();
        distances.addAll(sortedDistances);
        places.addAll(sortedPlaces);
    }

    public Places find(String match, List<String> type, List<String> where, int limit) throws BadRequestException {
        String types = "";
        String countries = "";
        if (type != null) {
            if(type.size()==1){
            types = " AND world.type LIKE " +"'%"+ type.get(0)+"%' ";
            } else if(type.size()>1){
                types = " AND (world.type LIKE " +"'%"+ type.get(0)+"%'";
                for(int i = 1; i<type.size();i++){
                    types+=" OR world.type LIKE "+"'%"+type.get(i)+"%'";
                }
            types+=") ";
            }
        }
        if (where != null) {
            if(where.size()==1){
            countries = " AND country.name LIKE " +"'%"+ where.get(0)+"%' ";
            } else if(where.size()>1){
                countries = " AND (country.name LIKE " +"'%"+ where.get(0)+"%'";
                for(int i = 1; i<where.size();i++){
                    countries+=" OR country.name LIKE "+"'%"+where.get(i)+"%'";
                }
            countries+=") ";
            }
        }
        String whereFind = "WHERE world.name LIKE "+"'%"+match+ "%'"+types+"  "+countries+"LIMIT "+limit+";";
        sendSQL send = new sendSQL();
        Places place = send.places(select + from + whereFind);
        found = send.found();
        return place;
    }
}
