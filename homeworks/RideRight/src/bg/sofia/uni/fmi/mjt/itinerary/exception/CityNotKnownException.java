package bg.sofia.uni.fmi.mjt.itinerary.exception;

public class CityNotKnownException extends Exception{
    public CityNotKnownException (String msg) {
        super(msg);
    }

    public CityNotKnownException (String msg, Throwable cause) {
        super(msg, cause);
    }
}
