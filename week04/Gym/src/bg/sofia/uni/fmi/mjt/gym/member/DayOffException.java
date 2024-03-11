package bg.sofia.uni.fmi.mjt.gym.member;

import java.time.DayOfWeek;

public class DayOffException extends RuntimeException{
    public DayOffException(String message) {
        super(message);
    }

    public DayOffException(String message, Throwable cause) {
        super(message, cause);
    }
}
