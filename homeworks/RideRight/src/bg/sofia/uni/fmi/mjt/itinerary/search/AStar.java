package bg.sofia.uni.fmi.mjt.itinerary.search;

import bg.sofia.uni.fmi.mjt.itinerary.City;
import bg.sofia.uni.fmi.mjt.itinerary.Journey;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class AStar {
    Map<City, List<Journey>> graph;

    public AStar(Map<City, List<Journey>> graph) {
        this.graph = graph;
    }

    public List<City> reconstructPath(Node start, Node destination) {
        ArrayList<City> path = new ArrayList<>();

        Node iter = destination;
        while(!iter.equals(start)) {
            path.add(iter.getCity());
            iter = iter.getParent();
        }

        return path;
    }

    public List<Journey> findShortestPath(City start, City destination) throws IllegalArgumentException{
        if (start == null || destination == null) {
            throw new IllegalArgumentException("Start and destination can't be null");
        }

        PriorityQueue<Node> openList = new PriorityQueue<>();
        PriorityQueue<Node> closedList = new PriorityQueue<>();

        Node startNode = new Node(start);
        startNode.setG(new BigDecimal(0));
        startNode.setH(startNode.calculateHeuristic(destination));
        startNode.setF(startNode.getH().add(startNode.getG()));
        openList.add(new Node(start));

        while (!openList.isEmpty()) {
            Node n = openList.peek();

            if (n.getCity() == destination) {
                ArrayList<Journey> path = new ArrayList<>();

                Node iter = n;
                while(!iter.equals(startNode)) {
                    path.add(iter.getJourneyToParent());
                    iter = iter.getParent();
                }

                return path;
            }

            for (Journey journey : graph.get(n.getCity())) {
                Node to = new Node(journey.to());
                BigDecimal totalWeight = n.getG().add(journey.getTotalPrice());

                if (!openList.contains(to) && !closedList.contains(to)) {
                    to.setParent(n);
                    to.setJourneyToParent(journey);
                    to.setG(totalWeight);
                    to.setH(to.calculateHeuristic(destination));
                    to.setF(to.getG().add(to.getH()));
                    openList.add(to);
                }
                else {
                    if (totalWeight.compareTo(to.getG()) < 0) {
                        to.setParent(n);
                        to.setJourneyToParent(journey);
                        to.setG(totalWeight);
                        to.setH(to.calculateHeuristic(destination));
                        to.setF(to.getG().add(to.getH()));

                        if (closedList.contains(to)) {
                            closedList.remove(to);
                            openList.add(to);
                        }
                    }
                }
            }

            openList.remove(n);
            closedList.add(n);
        }

        return null;
    }
}
