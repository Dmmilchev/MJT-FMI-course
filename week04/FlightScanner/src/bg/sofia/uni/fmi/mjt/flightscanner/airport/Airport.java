package bg.sofia.uni.fmi.mjt.flightscanner.airport;

public record Airport(String ID) {
    public Airport {
        if (ID == null) {
            throw new IllegalArgumentException("ID can't be null");
        }
        if (ID.isBlank()) {
            throw new IllegalArgumentException("ID can't be blank");
        }
        if (ID.isEmpty()) {
            throw new IllegalArgumentException("ID can't be empty");
        }
    }
}
