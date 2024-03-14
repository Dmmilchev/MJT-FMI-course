package bg.sofia.uni.fmi.mjt.flightscanner.flight;

import bg.sofia.uni.fmi.mjt.flightscanner.airport.Airport;
import bg.sofia.uni.fmi.mjt.flightscanner.exception.FlightCapacityExceededException;
import bg.sofia.uni.fmi.mjt.flightscanner.passenger.Gender;
import bg.sofia.uni.fmi.mjt.flightscanner.passenger.Passenger;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegularFlightTest {

    @Test
    void testConstructorShouldThrowExceptionIfPassedNullAirport() {
        assertThrows(IllegalArgumentException.class, () -> new RegularFlight(null, null, 0),
                "Constructor should throws IllegalArgumentException if from or to are null");
    }

    @Test
    void testConstructorShouldThrowExceptionIfPassedNegativeSeats() {
        Airport from = mock();
        Airport to = mock();
        assertThrows(IllegalArgumentException.class, () -> new RegularFlight(from, to,-1),
                "Constructor should throws IllegalArgumentException if number of seats is negative");
    }

    @Test
    void testConstructorShouldInitializePassengersAsEmptyList() {
        Airport from = mock();
        Airport to = mock();
        RegularFlight flight = new RegularFlight(from, to, 10);

        assertTrue(flight.getAllPassengers().isEmpty());
    }

    @Test
    void testAddPassengerShouldThrowExceptionIfNoSeatsLeft() {
        Airport from = mock();
        Airport to = mock();
        Passenger passenger = mock();
        RegularFlight flight = new RegularFlight(from, to, 0);

        assertThrows(FlightCapacityExceededException.class, () -> flight.addPassenger(passenger),
                "If totalSeats are 0 you can't add a new passenger");
    }

    @Test
    void testAddPassengerShouldAddPassengerInPassengersCollection() throws FlightCapacityExceededException{
        Airport from = mock();
        Airport to = mock();

        RegularFlight flight = new RegularFlight(from, to, 3);

        Passenger passenger = mock();
        when(passenger.id()).thenReturn("ID");
        when(passenger.name()).thenReturn("name");
        when(passenger.gender()).thenReturn(Gender.OTHER);

        flight.addPassenger(passenger);

        assertTrue(flight.getAllPassengers().contains(passenger));
    }
}
