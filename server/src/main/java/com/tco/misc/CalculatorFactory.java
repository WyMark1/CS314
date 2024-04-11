package com.tco.misc;
import com.tco.misc.BadRequestException;

public class CalculatorFactory {

    public CalculatorFactory() {}

    public static GreatCircleDistance get(String formula) throws BadRequestException {
        
        if (formula != null) {
            String lower = formula.toLowerCase();

            if (lower.equals("vincenty")) {
                return new vincenty();
            } else if (lower.equals("haversine")) {
                return new haversine();
            } else if (lower.equals("cosines")) {
                return new cosines();
            } else {
                BadRequestException BRE = new BadRequestException("Unsupported formula: " + formula);
                throw BRE;
            }
        } 
        else {
            return new vincenty();
        }

    }
}