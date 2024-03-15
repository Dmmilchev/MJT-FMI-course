package bg.sofia.uni.fmi.mjt.itinerary;

import bg.sofia.uni.fmi.mjt.itinerary.exception.CityNotKnownException;
import bg.sofia.uni.fmi.mjt.itinerary.exception.NoPathToDestinationException;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class RideRight implements ItineraryPlanner{
    Map<City, TreeSet<Journey>> roadNetwork;

    public RideRight() {
        roadNetwork = new HashMap<>();
    }

    public void addCity(City city) throws IllegalArgumentException{
        if (city == null) {
            throw new IllegalArgumentException("City can't be null");
        }
        roadNetwork.putIfAbsent(city, new TreeSet<>());
    }

    public void addJourney(Journey journey) throws IllegalArgumentException {
        if (journey == null) {
            throw new IllegalArgumentException("Journey can't be null");
        }

        addCity(journey.from());
        addCity(journey.to());

        roadNetwork.get(journey.from()).add(journey);
    }

    public Map<City, TreeSet<Journey>> getRoadNetwork() {
        return roadNetwork;
    }

    private Journey getCheapestPathWithoutTransfer(City start, City destination) throws NoPathToDestinationException {
        ArrayList<Journey> journeysFromStart = new ArrayList<>(roadNetwork.get(start));
        for (Journey journey : journeysFromStart) {
            if (journey.to().equals(destination)) {
                return journey;
            }
        }

        throw new NoPathToDestinationException("Could not find paths without transfer from start to destination");
    }
//
//    private SequencedCollection<Journey> getCheapestPathWithTransfer(City start, City destination) {
//
//    }
    /**
     * Returns a sequenced collection of Journeys representing the cheapest path from the start to the destination City.
     *
     * @param start         - City, from which the itinerary begins
     * @param destination   - the City that needs to be reached
     * @param allowTransfer - a flag parameter whether multiple Journeys with transfer can be returned as a result, or
     *                      only a direct route is expected
     * @throws CityNotKnownException        if the start or destination City is not present
     *                                      in the list of provided Journeys
     * @throws NoPathToDestinationException if there is no path satisfying the conditions
     */
    @Override
    public SequencedCollection<Journey> findCheapestPath(City start, City destination, boolean allowTransfer) throws CityNotKnownException, NoPathToDestinationException {
        ArrayList<Journey> result = new ArrayList<>();
        if(!allowTransfer) {
            result.add(getCheapestPathWithoutTransfer(start, destination));
            return result;
        }

        return result;
    }
}
