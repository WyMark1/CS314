package com.tco.misc;
import com.tco.misc.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorFactory {
    private static final transient Logger log = LoggerFactory.getLogger(CalculatorFactory.class);
    public CalculatorFactory() {}

    public static GreatCircleDistance get(String formula) throws BadRequestException {
        
        if (formula != null) {
            String lower = formula.toLowerCase();

            if (lower.equals("vincenty")) {
                log.info("vin");
                return new vincenty();
            } else if (lower.equals("haversine")) {
                log.info("haver");
                return new haversine();
            } else if (lower.equals("cosines")) {
                log.info("cos");
                return new cosines();
            } else {
                log.info("error in calcfactory");
                BadRequestException BRE = new BadRequestException("Unsupported formula: " + formula);
                throw BRE;
            }
        } 
        else {
            log.info("vin last");
            return new vincenty();
        }

    }
}