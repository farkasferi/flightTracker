package hu.myprojects.flighttracker.dao;

import hu.myprojects.flighttracker.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Flight findByCode(String code);

    Flight findByDepartureTime(Date date);
}
