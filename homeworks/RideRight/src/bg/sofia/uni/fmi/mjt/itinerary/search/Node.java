package bg.sofia.uni.fmi.mjt.itinerary.search;

import bg.sofia.uni.fmi.mjt.itinerary.City;

import java.util.ArrayList;

public class Node implements Comparable<Node> {
    private City city;
    private Node parent = null;
    private ArrayList<Edge> neighbours;
    private double f = Double.MAX_VALUE;
    private double h = Double.MAX_VALUE;

    public void setCity(City city) {
        this.city = city;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public void setNeighbours(ArrayList<Edge> neighbours) {
        this.neighbours = neighbours;
    }
    public void setF(double f) {
        this.f = f;
    }
    public void setH(double h) {
        this.h = h;
    }

    public City getCity() {
        return city;
    }
    public Node getParent() {
        return parent;
    }
    public ArrayList<Edge> getNeighbours() {
        return neighbours;
    }
    public double getF() {
        return f;
    }
    public double getH() {
        return h;
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(this.f, o.getF());
    }

    public void addBranch(int weight, Node node){
        Edge newEdge = new Edge(weight, node);
        neighbours.add(newEdge);
    }

    public double calculateHeuristic(Node target){
        return this.h;
    }
}
