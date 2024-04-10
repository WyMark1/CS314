package com.tco.requests;

import com.tco.misc.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindRequest extends Request {
    private String match;
    private List<String> type;
    private List<String> where;
    private Integer limit;
    private Integer found;
    private Places places;
    private static final transient Logger log = LoggerFactory.getLogger(FindRequest.class);

    public FindRequest(String match, List<String> type, List<String> where, Integer limit) {
        this.match = match;
        this.type = type;
        this.where = where;
        this.limit = limit;
    }
    
    public String getMatch() {
        return match;
    }

    public List getType() {
        return type;
    }

    public Integer getLimit() {
        return limit;
    }
    public List getWhere() {
        return where;
    }
    public Integer getFound() {
        return found;
    }
    @Override
    public void buildResponse() throws BadRequestException {
        this.requestType = "find";
        this.match = match;
        this.type = type;
        this.where = where;
        this.limit = limit;
        GeographicLocations geoLoc = new GeographicLocations();
        this.places = geoLoc.find(match,type,where,limit);
        this.found = places.size();
        log.trace("buildResponse -> {}", this);
    }

}