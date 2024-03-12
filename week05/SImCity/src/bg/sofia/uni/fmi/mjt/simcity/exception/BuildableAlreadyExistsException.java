package bg.sofia.uni.fmi.mjt.simcity.exception;

public class BuildableAlreadyExistsException extends Exception{
    public BuildableAlreadyExistsException(String msg) {
        super(msg);
    }
    public BuildableAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
