package hu.myprojects.flighttracker.exception;

import java.util.Map;

public class BusinessException extends RuntimeException {

    public BusinessException(String message, Class entityClass, Map<String, String> args) {
        super(message + " - EntityClass: " + entityClass.getSimpleName() + " - Args: " + args.toString());
    }

    public BusinessException(String message, Class entityClass) {
        super(message + " - EntityClass: " + entityClass.getSimpleName());
    }

    public BusinessException(String message) {
        super(message);
    }
}
