package com.tco.misc;

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

    public static GreatCircleDistance get(String formula) throws IllegalArgumentException {
        if (formula == null) {
            return new vincenty();
        }
        
        String lower = formula.toLowerCase();
        Supplier<GreatCircleDistance> supplier = formulaMap.get(lower);
        
        if (supplier != null) {
            return supplier.get();
        } else {
            throw new IllegalArgumentException("Unsupported formula: " + formula);
        }
    }
}
