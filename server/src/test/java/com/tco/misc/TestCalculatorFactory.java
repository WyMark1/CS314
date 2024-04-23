package com.tco.misc;
import com.tco.misc.CalculatorFactory;
import com.tco.misc.vincenty;
import com.tco.misc.cosines;
import com.tco.misc.haversine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import com.tco.misc.BadRequestException;
import static org.junit.jupiter.api.Assertions.*;

public class TestCalculatorFactory {
    
    @Test
    @DisplayName("wymark: returns vincenty when given null")
    public void testBaseCase() {
        try {
        CalculatorFactory calcFactory = new CalculatorFactory();
        assertTrue(calcFactory.get(null) instanceof vincenty);
        } catch(Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    @DisplayName ("wymark: returns a vincenty object when given formula vincenty")
    public void testFormulaIsVincenty() {
        try {
        CalculatorFactory calcFactory = new CalculatorFactory();
        assertTrue(calcFactory.get("Vincenty") instanceof vincenty);
    } catch(Exception e) {
        fail("Exception occurred: " + e.getMessage());
    }
    }

    @Test
    @DisplayName ("wymark: returns a haversine object when given formula haversine")
    public void testFormulaIsHaversine() {
        try {
        CalculatorFactory calcFactory = new CalculatorFactory();
        assertTrue(calcFactory.get("haversine") instanceof haversine);
    } catch(Exception e) {
        fail("Exception occurred: " + e.getMessage());
    }
    }

    @Test
    @DisplayName ("wymark: returns a cosines object when given formula cosines")
    public void testFormulaIsCosines() {
        try {
        CalculatorFactory calcFactory = new CalculatorFactory();
        assertTrue(calcFactory.get("cosines") instanceof cosines);
    } catch(Exception e) {
        fail("Exception occurred: " + e.getMessage());
    }
    }

    @Test
    @DisplayName("mstencel: Throws BadRequestException when given an incorrect formula")
    public void testIncorrectFormula() {
        CalculatorFactory calcFactory = new CalculatorFactory();
        try {
        calcFactory.get("Random Incorrect Formula");
        } catch(Exception e) {
            assertTrue(e instanceof BadRequestException);
        }
    }

}
