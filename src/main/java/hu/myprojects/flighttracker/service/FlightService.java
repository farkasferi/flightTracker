package hu.myprojects.flighttracker.service;

import hu.myprojects.flighttracker.domain.Flight;

public interface FlightService {

    long insertFlight(Flight flight);

    long updateFlight(Flight flight);

    void deleteFlight(long id);
}
