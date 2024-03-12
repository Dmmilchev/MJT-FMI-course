package bg.sofia.uni.fmi.mjt.simcity.exception;

public class BuildableNotFoundException extends Exception{

    public BuildableNotFoundException(String msg) {
        super(msg);
    }
    public BuildableNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
