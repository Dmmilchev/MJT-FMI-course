package bg.sofia.uni.fmi.mjt.itinerary;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {

    @Test
    void testManhattanDistanceShouldReturnZeroIfSameObjectIsPassed() {
            Location location = new Location(5, 5);
            assertEquals(0, Location.manhattanDistance(location, location),
                    "If same object has been passed, Manhattan distance should be 0");
    }

    @Test
    void testManhattanDistanceShouldReturnPositiveNumberIfLocationsAreDifferent() {
        Location location1 = new Location(10, 10);
        Location location2 = new Location(20, 20);
        assertEquals(20, Location.manhattanDistance(location1, location2),
                "If locations are different a positive number should be returned");
    }
}
