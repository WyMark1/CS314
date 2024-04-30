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
        Places sortedPlaces = removeExtraAndSortDistances(distanceList, s.places(sql), distance);
        return sortedPlaces;
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

    public Places removeExtraAndSortDistances(Distances distances, Places places, long distance) {
        TreeMap<Long, List<Place>> distanceMap = new TreeMap<>();

        for (int i = 0; i < distances.size(); i++) {
            long dist = distances.get(i);
            if (dist <= distance) {
                List<Place> placeList = distanceMap.computeIfAbsent(dist, k -> new ArrayList<>());
                placeList.add(places.get(i));
            }
        }

        places.clear();
        for (List<Place> placeList : distanceMap.values()) {
            places.addAll(placeList);
        }

        return places;
    }

    public String stringCreator(List<String> list){
        String sql = " AND (";
        for (int i = 0; i < list.size(); i++){
            sql += compareAgainstAll(list.get(i));
            if (i+1 !=list.size()) {
                sql += " OR ";
            }
        }
        sql += ")";
        return sql;
    }
    public Places find(String match, List<String> type, List<String> where, int limit) throws BadRequestException {
        String types = "";
        String countries = "";
        if (type != null && type.size() != 0) {
            types = stringCreator(type);
        }
        if (where != null && where.size() != 0) {
            countries = stringCreator(where);
        }

        String find = compareAgainstAll(match);
        String whereFind = "WHERE ("+find+") "+types+""+countries+" LIMIT "+limit+";";
        sendSQL send = new sendSQL();
        Places place = send.places(select + from + whereFind);
        found = send.found();
        if (limit == 0) {
            whereFind = "WHERE ("+find+") "+types+""+countries+" LIMIT "+found+";";
            place = send.places(select + from + whereFind);
        }
        return place;
    }

    private String compareAgainstAll(String match) {
        String[] compare = {"world.name", "world.municipality", "region.name", "country.name", "world.type"};
        String ret = "world.id LIKE '%" + match + "%' ";
        for(String type : compare) {
            ret += " OR " + type + " LIKE '%" + match + "%' ";
        }
        return ret;
    }
}
