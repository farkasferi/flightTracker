package hu.myprojects.flighttracker.dao;

import hu.myprojects.flighttracker.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Flight findByCode(String code);

    List<Flight> findByDepartureTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}
