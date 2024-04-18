package com.tco.misc;
import com.tco.misc.BadRequestException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CalculatorFactory {

    private static final Map<String, Supplier<GreatCircleDistance>> formulaMap = new HashMap<>();

    static {
        formulaMap.put("vincenty", vincenty::new);
        formulaMap.put("haversine", haversine::new);
        formulaMap.put("cosines", cosines::new);
    }

    public CalculatorFactory() {}

    public static GreatCircleDistance get(String formula) throws BadRequestException {
        if (formula == null) {
            return new vincenty();
        }
        
        String lower = formula.toLowerCase();
        Supplier<GreatCircleDistance> supplier = formulaMap.get(lower);
        
        if (supplier != null) {
            return supplier.get();
        } else {
            BadRequestException BRE = new BadRequestException("Unsupported formula: " + formula);
            throw BRE;
        }
    }
}
