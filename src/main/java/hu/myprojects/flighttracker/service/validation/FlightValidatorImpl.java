package hu.myprojects.flighttracker.service.validation;

import hu.myprojects.flighttracker.domain.Flight;
import hu.myprojects.flighttracker.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class FlightValidatorImpl implements FlightValidator {

    @Override
    public void validate(Flight flight){
        validateDepartureTime(flight.getDepartureTime());
    }

    private void validateDepartureTime(LocalDateTime dateTime) {
        if (dateTime.isBefore(LocalDateTime.now())) {
            Map<String, String> args = new HashMap<>();
            args.put("departureTime: ", dateTime.toString());
            throw new BusinessException("Departure time can't be in the past.", Flight.class, args);
        }
    }
}
