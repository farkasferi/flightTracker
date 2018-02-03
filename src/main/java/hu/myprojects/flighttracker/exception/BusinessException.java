package hu.myprojects.flighttracker.exception;

import java.util.Map;

public class BusinessException extends RuntimeException {

    public BusinessException(String message, Class entityClass, Map<String, String> args) {
        super(message + "\nEntityClass: " + entityClass.getSimpleName() + "\nArgs: " + args.toString());
    }

    public BusinessException(String message, Class entityClass) {
        super(message + "\nEntityClass: " + entityClass.getSimpleName());
    }

    public BusinessException(String message) {
        super(message);
    }
}
