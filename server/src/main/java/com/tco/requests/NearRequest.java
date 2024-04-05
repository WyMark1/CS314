package com.tco.requests;
import com.tco.requests.Place;
import com.tco.requests.Places;
import com.tco.requests.Distances;
import com.tco.misc.GeographicLocations;
import com.tco.misc.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearRequest extends Request{
    private Place place;
    private Integer distance;
    private double earthRadius;
    private String formula;
    private Integer limit;
    private Places places;
    private Distances distances;
    private static final transient Logger log = LoggerFactory.getLogger(NearRequest.class);

    public NearRequest(Place place, Integer distance, double earthRadius, String formula, Integer limit) {
        this.place = place;
        this.distance = distance;
        this.earthRadius = earthRadius;
        this.formula = formula;
        this.limit = limit;
    }
    
    public String getFormula() {
        return formula;
    }

    public Places getPlaces() {
        return places;
    }

    public Distances getDistances() {
        return distances;
    }

    @Override
    public void buildResponse() throws BadRequestException {
        this.requestType = "near";
        this.place = place;
        this.distance = distance;
        this.earthRadius = earthRadius;
        if (formula != null) {
            this.formula = formula;
        }
        this.limit = limit;
        GeographicLocations geoLoc = new GeographicLocations();
        this.places = geoLoc.near(place, distance, earthRadius, formula, limit);
        this.distances = geoLoc.distances(place, places);
        log.trace("buildResponse -> {}", this);
    }
}
