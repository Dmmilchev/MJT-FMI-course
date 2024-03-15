package bg.sofia.uni.fmi.mjt.itinerary;

public record Location(int x, int y) {
    public static int manhattanDistance(Location location1, Location location2) {
        return  Math.abs(location1.x() - location2.x()) +
                Math.abs(location1.y() - location2.y());
    }
}
