package com.tco.misc;
import com.tco.misc.CalculatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCalculatorFactory {
    
    @Test
    @DisplayName("wymark: returns null when given an incorrect formula")
    public void testBaseCase() {
        CalculatorFactory calcFactory = new CalculatorFactory();
        assertTrue(calcFactory.get("Random Incorrect Formula") == null);
    }

}
