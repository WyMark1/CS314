package com.tco.misc;

public class OptimizerFactory {
    public OptimizerFactory(){}

    public TourOptimizer get(int N, Double response){
        if (N == 0) {
            return new NoOptimizer();
        } else if (N == 2) {
            return new TwoOptimizer();
        } else if (N == 3) {
            return new ThreeOptimizer();
        } else {
            return null;
        }   
    }
}
