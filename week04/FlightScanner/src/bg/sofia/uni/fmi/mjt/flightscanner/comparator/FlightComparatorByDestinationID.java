package bg.sofia.uni.fmi.mjt.flightscanner.comparator;

import bg.sofia.uni.fmi.mjt.flightscanner.FlightScanner;
import bg.sofia.uni.fmi.mjt.flightscanner.airport.Airport;
import bg.sofia.uni.fmi.mjt.flightscanner.flight.Flight;

import java.util.Comparator;

public class FlightComparatorByDestinationID implements Comparator<Flight> {
    @Override
    public int compare(Flight o1, Flight o2) {
        return o1.getTo().ID().compareTo(o2.getTo().ID());
    }
}
