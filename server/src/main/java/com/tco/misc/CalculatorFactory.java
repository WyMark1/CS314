package com.tco.misc;

public class CalculatorFactory {

    public CalculatorFactory() {}

    public GreatCircleDistance get(String formula) {
        
        String lower = formula.toLowerCase();

        if (lower.equals("vincenty")) {
            return new vincenty();
        } else if (lower.equals("haversine")) {
            return new haversine();
        } else if (lower.equals("cosines")) {
            return new cosines();
        } else {
            return null;
        }
    }
}