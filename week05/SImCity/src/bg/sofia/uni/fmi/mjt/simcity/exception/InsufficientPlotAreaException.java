package bg.sofia.uni.fmi.mjt.simcity.exception;

public class InsufficientPlotAreaException extends Exception{
    public InsufficientPlotAreaException(String msg) {
        super(msg);
    }
    public InsufficientPlotAreaException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
