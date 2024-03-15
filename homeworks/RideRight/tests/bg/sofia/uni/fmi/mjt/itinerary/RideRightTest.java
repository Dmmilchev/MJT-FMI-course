package bg.sofia.uni.fmi.mjt.itinerary;

import bg.sofia.uni.fmi.mjt.itinerary.exception.NoPathToDestinationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class RideRightTest {

    @Test
    void testGetCheapestPathWithoutTransferShouldThrowExceptionIfThereIsNotAPath() {
        RideRight rr = new RideRight();
        City city1 = mock();
        City city2 = mock();
        assertThrows(NoPathToDestinationException.class, () -> rr.findCheapestPath(city1, city2, Boolean.FALSE),
                "If road network is empty there must be no path");
    }

}
