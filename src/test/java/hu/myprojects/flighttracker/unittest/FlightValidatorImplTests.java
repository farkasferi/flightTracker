package hu.myprojects.flighttracker.unittest;

import hu.myprojects.flighttracker.UnitTest;
import hu.myprojects.flighttracker.domain.Flight;
import hu.myprojects.flighttracker.exception.BusinessException;
import hu.myprojects.flighttracker.service.validation.FlightValidatorImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Category(UnitTest.class)
public class FlightValidatorImplTests {

    private static FlightValidatorImpl validator;

    @BeforeClass
    public static void beforeClass() {
        validator = new FlightValidatorImpl();
    }

    @Test
    public void ValidatorValidatesDepartureTimePositiveTest() {
        //given a Flight
        Flight flight = new Flight("TestFlight", LocalDateTime.now().plusDays(1));

        //when I call the validator
        validator.validate(flight);

        //then it runs without exception
    }

    @Test(expected = BusinessException.class)
    public void ValidatorValidatesDepartureTimeNegativeTest() {
        //given a Flight
        Flight flight = new Flight("TestFlight", LocalDateTime.now().minusDays(1));

        //when I call the validator
        validator.validate(flight);

        //then the validator throws an exception
    }
}
