package bg.sofia.uni.fmi.mjt.itinerary.search;

import bg.sofia.uni.fmi.mjt.itinerary.City;
import bg.sofia.uni.fmi.mjt.itinerary.Journey;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;

public class Graph {
    private ArrayList<Node> nodes;

    public Graph(Map<City, TreeSet<Journey>> roadNetwork) {
        // TODO
        // When a road network is passed we should create a graph that represents it
    }

    public ArrayList<Node> getShortestPath(Node start, Node target) {
        // TODO
        // Implement find the short path from start to target
        return new ArrayList<>();
    }

    public static ArrayList<City> transformPath (ArrayList<Node> path) {
        // TODO
        // From a list of nodes get the city list
        return new ArrayList<>();
    }
}
