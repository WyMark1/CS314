package com.tco.misc;
import com.tco.misc.CalculatorFactory;
import com.tco.misc.OptimizerFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestOptimizerFactory {
    @Test
    @DisplayName("Josh1302: returns null when given an incorrect formula")
    public void testBaseCase() {
        OptimizerFactory optFactory = new OptimizerFactory();
        assertTrue(optFactory.get(5,10.0) == null);
    }
}