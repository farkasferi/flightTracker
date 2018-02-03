package hu.myprojects.flighttracker.integtest;

import hu.myprojects.flighttracker.dao.FlightRepository;
import hu.myprojects.flighttracker.domain.Flight;
import hu.myprojects.flighttracker.exception.BusinessException;
import hu.myprojects.flighttracker.service.FlightService;
import hu.myprojects.flighttracker.IntegrationTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Category(IntegrationTest.class)
@TestPropertySource(locations = "classpath:integration-test.properties")
public class FlightServiceTests {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository repository;

    @Test
    public void insertFlightInsertsNewValidFlight() {
        //given a new valid flight
        Flight flight = new Flight("ValidFlight", LocalDateTime.now());

        //when I insert it via the service
        long flightId = flightService.insertFlight(flight);

        //then I can retrieve it from database
        assert repository.findById(flightId).isPresent();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void insertFlightFailsForInvalidFlight() {
        //given a new invalid flight (missing code)
        Flight flight = new Flight(null, LocalDateTime.now());

        //when I insert it via the service
        flightService.insertFlight(flight);

        //then I get an exception
    }

    @Test(expected = BusinessException.class)
    public void insertFlightFailsForFlightThatAlreadyExist() {
        //given a persisted flight
        Flight flight = new Flight("TestFlight", LocalDateTime.now());
        Flight persistedFlight = repository.save(flight);

        //when I insert it via the service
        flightService.insertFlight(persistedFlight);

        //then I get an exception
    }

    @Test
    public void updateFlightUpatesAnExistingFlight() {
        //given a persisted flight
        Flight flight = new Flight("PersistedFlight", LocalDateTime.now());
        Flight persistedFlight = repository.save(flight);

        //when I call the update service
        Flight modifiedFlight = new Flight("ModifiedFlight", LocalDateTime.now());
        modifiedFlight.setId(persistedFlight.getId());
        flightService.updateFlight(modifiedFlight);

        //then the flight is updated
        Optional<Flight> updatedFlight = repository.findById(persistedFlight.getId());
        assert updatedFlight.isPresent();
        assert updatedFlight.get().getCode().equals("ModifiedFlight");
    }

    @Test
    public void deleteFlightDeletesExistingFlight() {
        //given a persisted flight
        Flight flight = new Flight("PersistedFlight", LocalDateTime.now());
        Flight persistedFlight = repository.save(flight);

        //when I call the delete service
        flightService.deleteFlight(persistedFlight.getId());

        //then the flight is deleted
        assert !repository.findById(persistedFlight.getId()).isPresent();
    }
}
