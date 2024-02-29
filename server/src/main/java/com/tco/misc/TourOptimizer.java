package com.tco.misc;
import com.tco.requests.Places;

public abstract class TourOptimizer {

    public Places construct(Places places, Double radius, String formula, Double response) {
        return places;
    }

    public abstract void improve();

}
