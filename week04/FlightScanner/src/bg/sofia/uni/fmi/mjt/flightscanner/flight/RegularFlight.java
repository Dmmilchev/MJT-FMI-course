package bg.sofia.uni.fmi.mjt.flightscanner.flight;

import bg.sofia.uni.fmi.mjt.flightscanner.airport.Airport;
import bg.sofia.uni.fmi.mjt.flightscanner.exception.FlightCapacityExceededException;
import bg.sofia.uni.fmi.mjt.flightscanner.passenger.Passenger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RegularFlight implements Flight{
    private final Airport from;
    private final Airport to;
    private final int totalSeats;
    private List<Passenger> passengers;

    public RegularFlight(Airport from, Airport to, int totalSeats) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("End point destinations can't be null");
        }
        if (totalSeats < 0) {
            throw new IllegalArgumentException("Number of seats can't be negative");
        }

        this.from = from;
        this.to = to;
        this.totalSeats = totalSeats;
        passengers = new ArrayList<>(totalSeats);
    }

    /**
     * Gets the start airport of this flight.
     */
    @Override
    public Airport getFrom() {
        return from;
    }

    /**
     * Gets the destination airport of this flight.
     */
    @Override
    public Airport getTo() {
        return to;
    }

    /**
     * Adds a passenger to this flight.
     *
     * @param passenger the passenger to add.
     * @throws FlightCapacityExceededException, if the flight is already fully booked (there are no free seats).
     */
    @Override
    public void addPassenger(Passenger passenger) throws FlightCapacityExceededException {
        if (passengers.size() >= totalSeats) {
            throw new FlightCapacityExceededException("Maximum number of passengers has been reached");
        }
        passengers.add(passenger);
    }

    /**
     * Adds a collection of passengers to this flight.
     *
     * @param passengers the passengers to add.
     * @throws FlightCapacityExceededException, in case the flight cannot accommodate that many passengers
     *                                          (the available free seats are not enough).
     */
    @Override
    public void addPassengers(Collection<Passenger> passengers) throws FlightCapacityExceededException {
        if (this.passengers.size() + passengers.size() >= totalSeats) {
            throw new FlightCapacityExceededException("Not enough free seats for everyone");
        }
        this.passengers.addAll(passengers);
    }

    /**
     * Gets all passengers on this flight. If there are no passengers, returns an empty list.
     *
     * @return a collection of all passengers on this flight, as an unmodifiable copy.
     */
    @Override
    public Collection<Passenger> getAllPassengers() {
        return Collections.unmodifiableCollection(passengers);
    }

    /**
     * Gets the number of free seats on this flight.
     */
    @Override
    public int getFreeSeatsCount() {
        return totalSeats - passengers.size();
    }
}
