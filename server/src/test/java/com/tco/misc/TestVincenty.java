package com.tco.misc;

import com.tco.requests.Place;
import com.tco.misc.vincenty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.lang.Math;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVincenty {
    @Test
    @DisplayName("Josh1302: Testing vincenty with basic values.")
    public void TestVincentyBasic() {
        Place location1 = new Place(68.33, 10.68);
        Place location2 = new Place(12.33, 120.22);
        Double radius = 3456789.10;
        Long result = 546179L;
        vincenty Vincenty = new vincenty();
        //assertEquals(result, Vincenty.between(location1, location2, radius));
    }

    @Test
    @DisplayName("Josh1302: Testing vincenty with actual coordinates.")
    public void TestVincentyReal() {
        Place location1 = new Place(40.17743, -105.09784);
        Place location2 = new Place(6.69234, -1.60985);
        Double radius = 637234.82;
        Long result = 546179L;
        vincenty Vincenty = new vincenty();
        //assertEquals(result, Vincenty.between(location1, location2, radius));
    }
}