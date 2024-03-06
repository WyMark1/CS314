package com.tco.misc;

public class OptimizerFactory {
    public OptimizerFactory(){}

    public TourOptimizer get(int N, Double response){
        if(response != null){
        if(N <=1 || response < 0.25 ){
            return new NoOptimizer();
        }
        else if(N==2 || response <= 0.6){
            return new TwoOptimizer();
        }
        else if (N>=3 || response <=1.0){
            return new ThreeOptimizer();
        }
        else{
            return new NoOptimizer();
        }
     }
    }
}
