package com.tco.requests;

import com.tco.requests.Distances;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDistancesTotal {

    @Test
    @DisplayName("hca1197: test distances total with empty array.")
    public void testEmptyTotal() {
        Distances distances = new Distances();
        assertEquals(0, distances.total());
    }

    @Test
    @DisplayName("hca1197: test distances total with single element array.")
    public void testSingleTotal() {
        Distances distances = new Distances();
        distances.add(10L);
        assertEquals(10, distances.total());
    }

    @Test
    @DisplayName("hca1197: test distances total with multiple elements.")
    public void testMultipleTotal() {
        Distances distances = new Distances();
        distances.add(10L);
        distances.add(2000L);
        distances.add(50000L);
        assertEquals(52010, distances.total());
    }

    @Test
    @DisplayName("hca1197: test distances total with negative elements.")
    public void testNegativeTotal() {
        Distances distances = new Distances();
        distances.add(-10L);
        distances.add(20L);
        distances.add(-30L);
        assertEquals(-20, distances.total());
    }
}