package com.tco.requests;
import com.tco.requests.NearRequest;
import com.tco.requests.Places;
import com.tco.requests.Places;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNearRequest {

    @Test
    @DisplayName("wymark: Testing no places given if there are none.")
    public void TestNoPlace() {
        Place place = new Place(0.0, 0.0);
        Integer distance = 1;
        double earthRadius = 3959;
        String formula = null;
        Integer limit = 1;
        NearRequest request = new NearRequest(place, distance, earthRadius, formula, limit);
        request.buildResponse();
        assertEquals(request.getPlaces().size(), 0);
    }

}