package bg.sofia.uni.fmi.mjt.flightscanner;

import bg.sofia.uni.fmi.mjt.flightscanner.airport.Airport;
import bg.sofia.uni.fmi.mjt.flightscanner.comparator.FlightComparatorByDestinationID;
import bg.sofia.uni.fmi.mjt.flightscanner.comparator.FlightComparatorByFreeSeats;
import bg.sofia.uni.fmi.mjt.flightscanner.flight.Flight;

import java.util.*;

public class FlightScanner implements FlightScannerAPI{
    private final Map<Airport, Set<Flight>> flights;
    public FlightScanner() {
        flights = new HashMap<>();
    }
    /**
     * Adds a flight. If the same flight has already been added, call does nothing.
     *
     * @param flight the flight to add.
     * @throws IllegalArgumentException if flight is null.
     */
    @Override
    public void add(Flight flight) {
        if (flight == null) {
            throw new IllegalArgumentException("Flights can't be null");
        }
        Airport from = flight.getFrom();

        flights.putIfAbsent(from, new HashSet<>());
        flights.get(from).add(flight);    }

    /**
     * Adds all flights specified. If any of the flights have already been added, those are ignored.
     *
     * @param flights the flights to be added.
     * @throws IllegalArgumentException if flights is null.
     */
    @Override
    public void addAll(Collection<Flight> flights) {
        if (flights == null) {
            throw new IllegalArgumentException("Flights can't be null");
        }


        for (Flight f : flights) {
            add(f);
        }
    }

    private List<Flight> bfs(Airport current, Airport target) {
        Set<Airport> visited = new HashSet<>();
        Queue<Airport> queue = new LinkedList<>();
        Map<Airport, Flight> parentOf = new HashMap<>();

        visited.add(current);
        queue.add(current);
        parentOf.put(current, null);

        while (!queue.isEmpty()) {
            Airport airport = queue.poll();

            if (airport.equals(target)) {
                List<Flight> flightPlan = new LinkedList<>();

                while (parentOf.get(target) != null) {
                    flightPlan.add(parentOf.get(target));
                    target = parentOf.get(target).getFrom();
                }

                Collections.reverse(flightPlan);

                return flightPlan;
            }

            Set<Flight> outgoingFlights = flights.get(airport);

            if (outgoingFlights == null) {
                continue;
            }

            for (Flight outgoingFlight : outgoingFlights) {
                if (!visited.contains(outgoingFlight.getTo())) {
                    visited.add(outgoingFlight.getTo());
                    queue.add(outgoingFlight.getTo());
                    parentOf.put(outgoingFlight.getTo(), outgoingFlight);
                }
            }
        }

        return Collections.emptyList();
    }

    /**
     * Returns a list of flights from start to destination airports with minimum number of transfers.
     * If there are several such flights with equal minimum number of transfers, returns any of them.
     * If the destination is not reachable with any sequence of flights from the start airport,
     * returns an empty list.
     *
     * @param from the start airport.
     * @param to   the destination airport.
     * @throws IllegalArgumentException if from or to is null, or if from and to are one and the same airport.
     */
    @Override
    public List<Flight> searchFlights(Airport from, Airport to) {
        if (from == to) {
            throw new IllegalArgumentException("End point destinations can't be the same");
        }
        if (from == null || to == null) {
            throw new IllegalArgumentException("End point destinations can't be null");
        }

        return bfs(from, to);
    }

    /**
     * Gets an unmodifiable copy of all outbound flights from the specified airport,
     * sorted by number of free seats, in descending order.
     *
     * @param from the airport.
     * @throws IllegalArgumentException if from is null.
     */
    @Override
    public List<Flight> getFlightsSortedByFreeSeats(Airport from) {
        if (from == null) {
            throw new IllegalArgumentException("From airport cannot be null");
        }

        Set<Flight> res = flights.get(from);

        if (res == null) {
            return List.of();
        }

        List<Flight> sorted = new ArrayList(res);
        Collections.sort(sorted, new FlightComparatorByFreeSeats().reversed());
        return List.copyOf(sorted);
    }

    /**
     * Gets an unmodifiable copy of all outbound flights from the specified airport,
     * sorted by destination airport ID, in lexicographic order.
     *
     * @param from the airport.
     * @throws IllegalArgumentException if from is null.
     */
    @Override
    public List<Flight> getFlightsSortedByDestination(Airport from) {
        if (from == null) {
            throw new IllegalArgumentException("From airport cannot be null");
        }

        Set<Flight> res = flights.get(from);

        if (res == null) {
            return List.of();
        }

        List<Flight> sorted = new ArrayList(res);
        Collections.sort(sorted, new FlightComparatorByDestinationID());
        return List.copyOf(sorted);
    }
}
