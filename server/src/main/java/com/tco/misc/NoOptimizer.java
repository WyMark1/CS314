package com.tco.misc;
import com.tco.misc.TourOptimizer;
import com.tco.requests.Places;
import com.tco.requests.Place;

public class NoOptimizer extends TourOptimizer {
  
    public NoOptimizer () {}
    
    public void improve(Places places, long[][] distances, int[] tour) {}
}