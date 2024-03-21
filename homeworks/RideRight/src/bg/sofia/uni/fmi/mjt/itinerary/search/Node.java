package bg.sofia.uni.fmi.mjt.itinerary.search;

import bg.sofia.uni.fmi.mjt.itinerary.City;
import bg.sofia.uni.fmi.mjt.itinerary.Journey;
import bg.sofia.uni.fmi.mjt.itinerary.Location;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static bg.sofia.uni.fmi.mjt.itinerary.Location.manhattanDistance;

public class Node implements Comparable<Node> {
    private static final String SENTINEL = "9999999999999999999";
    private City city;
    private Node parent = null;
    private Journey journeyToParent = null;
    private List<Journey> neighbours;
    private BigDecimal f;
    private BigDecimal g;
    private BigDecimal h;


    public Node(City city) {
        this.city = city;
        f = new BigDecimal(SENTINEL);
        g = new BigDecimal(SENTINEL);
        h = new BigDecimal(SENTINEL);
    }
    public void setCity(City city) {
        this.city = city;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public void setJourneyToParent(Journey journeyToParent) {
        this.journeyToParent = journeyToParent;
    }

    public void setNeighbours(List<Journey> neighbours) {
        this.neighbours = neighbours;
    }
    public void setF(BigDecimal f) {
        this.f = f;
    }
    public void setG(BigDecimal g) {
        this.g = g;
    }
    public void setH(BigDecimal h) {
        this.h = h;
    }

    public City getCity() {
        return city;
    }
    public Node getParent() {
        return parent;
    }
    public Journey getJourneyToParent() {
        return journeyToParent;
    }
    public List<Journey> getNeighbours() {
        return neighbours;
    }
    public BigDecimal getF() {
        return f;
    }
    public BigDecimal getG() { return g; }
    public BigDecimal getH() {
        return h;
    }

    @Override
    public int hashCode() {
        return city.hashCode() + f.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (! (obj instanceof  Node)) {
            return false;
        }

        return this.city.equals(((Node) obj).getCity());
    }

    @Override
    public int compareTo(Node o) {
        return this.f.compareTo(o.getF());
    }

    public BigDecimal calculateHeuristic(City target){
        BigDecimal averageCost = new BigDecimal("0.02");
        return new BigDecimal(manhattanDistance(this.city.location(), target.location())).multiply(averageCost);
    }

}
