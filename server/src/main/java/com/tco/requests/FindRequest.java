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

    @Override
    public void buildResponse() {
        this.requestType = "find";
    }

}