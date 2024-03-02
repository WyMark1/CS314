package com.tco.misc;
import com.tco.misc.CalculatorFactory;
import com.tco.misc.vincenty;
import com.tco.misc.cosines;
import com.tco.misc.haversine;
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

    @Test
    @DisplayName ("wymark: returns a vincenty object when given formula vincenty")
    public void testFormulaIsVincenty() {
        CalculatorFactory calcFactory = new CalculatorFactory();
        assertTrue(calcFactory.get("Vincenty") instanceof vincenty);
    }

    @Test
    @DisplayName ("wymark: returns a haversine object when given formula haversine")
    public void testFormulaIsHaversine() {
        CalculatorFactory calcFactory = new CalculatorFactory();
        assertTrue(calcFactory.get("haversine") instanceof haversine);
    }

    @Test
    @DisplayName ("wymark: returns a cosines object when given formula cosines")
    public void testFormulaIsCosines() {
        CalculatorFactory calcFactory = new CalculatorFactory();
        assertTrue(calcFactory.get("cosines") instanceof cosines);
    }

}
