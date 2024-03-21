package bg.sofia.uni.fmi.mjt.itinerary;


import java.math.BigInteger;

public record Location(BigInteger x, BigInteger y) {
    public static BigInteger manhattanDistance(Location location1, Location location2) {
        return  (location1.x().subtract(location2.x())).abs().add(
                (location1.y().subtract(location2.y())).abs());
    }
}
