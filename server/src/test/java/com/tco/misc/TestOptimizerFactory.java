package com.tco.misc;
import com.tco.misc.CalculatorFactory;
import com.tco.misc.OptimizerFactory;
import com.tco.misc.NoOptimizer;
import com.tco.misc.TwoOptimizer;
import com.tco.misc.ThreeOptimizer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestOptimizerFactory {
    @Test
    @DisplayName("Josh1302: returns NoOpt when given incorrect values")
    public void testBaseNoOpt() {
        OptimizerFactory optFactory = new OptimizerFactory();
        assertTrue(optFactory.get(5,10.0) instanceof NoOptimizer);
    }
    @Test
    @DisplayName("Josh1302: returns NoOpt when given correct values")
    public void testNoOPt() {
        OptimizerFactory optFactory = new OptimizerFactory();
        assertTrue(optFactory.get(0,0.15) instanceof NoOptimizer);
    }
    @Test
    @DisplayName("Josh1302: returns TwoOpt when given correct values")
    public void testTwoOpt() {
        OptimizerFactory optFactory = new OptimizerFactory();
        assertTrue(optFactory.get(2,0.37) instanceof TwoOptimizer);
    }
    @Test
    @DisplayName("Josh1302: returns ThreeOpt when given correct values")
    public void testThreeOpt() {
        OptimizerFactory optFactory = new OptimizerFactory();
        assertTrue(optFactory.get(3,0.9) instanceof ThreeOptimizer);
    }
}
