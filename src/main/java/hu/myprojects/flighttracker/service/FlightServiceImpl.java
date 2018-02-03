package hu.myprojects.flighttracker.service;

import hu.myprojects.flighttracker.dao.FlightRepository;
import hu.myprojects.flighttracker.domain.Flight;
import hu.myprojects.flighttracker.exception.BusinessException;
import hu.myprojects.flighttracker.service.validation.FlightValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightValidator validator;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public long insertFlight(Flight flight){
        validator.validate(flight);
        checkId(flight);
        Flight persistedFlight = flightRepository.save(flight);
        return persistedFlight.getId();
    }

    private void checkId(Flight flight){
        if (flight.getId() > 0) {
            Map<String, String> args = new HashMap<>();
            args.put("Id:", Long.toString(flight.getId()));
            throw new BusinessException("Flight already exists.", Flight.class, args);
        }
    }

    @Override
    public long updateFlight(Flight flight) {
        validator.validate(flight);
        Flight persistedFlight = flightRepository.save(flight);
        return persistedFlight.getId();
    }

    @Override
    public void deleteFlight(long id) {
        flightRepository.deleteById(id);
    }
}
