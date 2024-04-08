package com.tco.misc;

import com.tco.requests.Places;
import com.tco.requests.Place;
import com.tco.requests.Distances;
import com.tco.misc.sendSQL;
import com.tco.misc.BadRequestException;
import java.lang.Math;

public class GeographicLocations {

    public GeographicLocations() {

    }

    public Places near(Place place, int distance, double eathRadius, String formula, int limit) throws BadRequestException {
        String lat = place.get("latitude");
        String lon = place.get("longitude");
        double latDelta = (distance / eathRadius) * (180 / Math.PI);
        double lonDelta = (distance / eathRadius) * (180 / Math.PI) / Math.cos(place.latRadians());
        String select = "SELECT world.id, world.name, world.municipality, region.name AS region, country.name AS country, world.latitude, world.longitude, world.altitude, world.type, ABS(world.latitude - "+lat+") AS lat_diff,  ABS(world.longitude - "+lon+") AS lon_diff ";
        String from = "FROM continent INNER JOIN country ON continent.id = country.continent INNER JOIN region ON country.id = region.iso_country INNER JOIN world ON region.id = world.iso_region ";
        String where =  "WHERE world.latitude BETWEEN " +lat+ " - "+latDelta+" AND "+lat+" + "+latDelta+"  AND world.longitude BETWEEN " +lon+ " - "+ lonDelta +" AND "+lon+" + " + lonDelta;
        String orderBy = " ORDER BY lat_diff + lon_diff LIMIT "+Integer.toString(limit) +";";
        String sql = select + from + where + orderBy;
        sendSQL s = new sendSQL();
        return s.places(sql);
    }

    public Distances distances(Place place, Places places) {
        Distances distances = new Distances();
        return distances;
    }

    public Integer found() {
        return 0;
    }
    
    public Places find(String match, String type, String where, int limit) {
        Places places = new Places();
        return places;
    }
}
