package hu.myprojects.flighttracker.service.validation;

import hu.myprojects.flighttracker.domain.Flight;

public interface FlightValidator {

    void validate(Flight flight);
}
