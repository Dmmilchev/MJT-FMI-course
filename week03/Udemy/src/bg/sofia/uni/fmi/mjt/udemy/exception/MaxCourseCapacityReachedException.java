package bg.sofia.uni.fmi.mjt.udemy.exception;

import java.rmi.server.ExportException;

public class MaxCourseCapacityReachedException extends Exception {
    public MaxCourseCapacityReachedException(String message) {
        super(message);
    }
}
