package com.tco.misc;

import com.tco.requests.Places;
import com.tco.requests.Place;
import com.tco.requests.Distances;
import com.tco.misc.sendSQL;
import com.tco.misc.BadRequestException;

public class GeographicLocations {

    public GeographicLocations() {

    }

    public Places near(Place place, int distance, double eathRadius, String formula, int limit) throws BadRequestException {
        Places places = new Places();
        return places;
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
