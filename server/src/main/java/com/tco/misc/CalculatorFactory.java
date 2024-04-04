package com.tco.misc;

public class CalculatorFactory {

    public CalculatorFactory() {}

    public static GreatCircleDistance get(String formula) throws IllegalArgumentException {
        
        if (formula != null) {
            String lower = formula.toLowerCase();

            if (lower.equals("vincenty")) {
                return new vincenty();
            } else if (lower.equals("haversine")) {
                return new haversine();
            } else if (lower.equals("cosines")) {
                return new cosines();
            } else {
                throw new IllegalArgumentException("Unsupported formula: " + formula);
            }
        } 
        else {
            return new vincenty();
        }

    }
}