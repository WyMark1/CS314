package com.tco.misc;
import com.tco.requests.Places;
import com.tco.requests.Place;
import com.tco.misc.CalculatorFactory;
import com.tco.misc.GreatCircleDistance;
import java.util.stream.IntStream;
import java.util.Collections;

public abstract class TourOptimizer {
    public Places construct(Places places, Double radius, String formula, Double response) throws BadRequestException {
        if (radius < 1.0 || response < 0.0) { 
            throw new IllegalArgumentException("Radius must be at least 1 and response must be positive.");
        }

        GreatCircleDistance calculator = CalculatorFactory.get(formula);
        if (calculator == null) {
            throw new IllegalArgumentException("Unsupported formula: " + formula);
        }

        if (places.size() < 4 || response == 0.0) return places;
        long[][] distances = getDistances(places, calculator, radius);
        places = applyNearestNeighborOptimization(places, distances, response); 
        return places;
    }

    public abstract void improve(Places places, long[][] distances, int[] tour);

    private Places applyNearestNeighborOptimization(Places places, long[][] distances, Double response) {
        int size = places.size();
        Places new_places = new Places();
        int[] best_tour = IntStream.range(0, size).toArray();
        long min_distance = getTotalDistance(distances, IntStream.range(0, size).toArray());
        double num_places_double = (600.994 * response) + 102.436;
        int num_places = (int) Math.round(num_places_double);

        for (int i = 0; i < size; i++) {
            if (i == num_places) { 
                break;
            }

            int[] current_tour = IntStream.range(0, size).toArray();
            if (i != 0) {
                int save = current_tour[0];
                current_tour[0] = current_tour[i];
                current_tour[i] = save;
            }

            for (int j = 0; j < size - 1; j++) {
                int from = current_tour[j];
                long best_distance = distances[from][current_tour[j+1]];
                int best_place = j+1;
                for (int k = j + 1 ; k < size; k++) {
                    int to = current_tour[k];
                    if (distances[from][to] < best_distance) {
                        best_distance = distances[from][to];
                        best_place = k;
                    }
                }

                int save = current_tour[j+1];
                current_tour[j+1] = current_tour[best_place];
                current_tour[best_place] = save;
            }

            improve(places, distances, current_tour);
            long current_min_distance = getTotalDistance(distances, current_tour);

            if (current_min_distance < min_distance) {
                min_distance = current_min_distance;
                best_tour = current_tour;
            }
        }

        for (int i = 0; i < size; i++) {
            new_places.add(places.get(best_tour[i]));
        }

        int index = new_places.indexOf(places.get(0));
        Collections.rotate(new_places, -index);

        return new_places;
    }

    private long[][] getDistances(Places places, GreatCircleDistance calculator, Double radius) {
        int size = places.size();
        long[][] distances = new long[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                distances[i][j] = calculator.between(places.get(i), places.get(j), radius);
            }
        }
        return distances;
    }

    private int getTotalDistance(long[][] distances, int[] tour) {
        int totalDistance = 0;
        for (int i = 0; i < tour.length-1; i++) {
            totalDistance += distances[tour[i]][tour[i+1]];
        }
        
        totalDistance += distances[tour.length-1][0];
        return totalDistance;
    }
}
