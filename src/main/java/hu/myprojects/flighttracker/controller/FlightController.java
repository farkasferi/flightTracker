package hu.myprojects.flighttracker.controller;

import hu.myprojects.flighttracker.dao.FlightRepository;
import hu.myprojects.flighttracker.domain.Flight;
import hu.myprojects.flighttracker.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    @RequestMapping(value = "/flight", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertFlight(@RequestBody Flight flight){
        flightService.insertFlight(flight);
    }

    @RequestMapping(value = "/flight/update", method = RequestMethod.POST)
    public void updateFlight(@RequestBody Flight flight) {
        flightService.updateFlight(flight);
    }

    @RequestMapping(value = "/flight/delete", method = RequestMethod.DELETE)
    public void deleteFlight(@RequestParam Long id) {
        flightService.deleteFlight(id);
    }

    @RequestMapping(value = "/flight/list", method = RequestMethod.GET)
    public @ResponseBody Iterable<Flight> listFlight() {
        return flightRepository.findAll();
    }

    @RequestMapping(value = "/flight/get/interval", method = RequestMethod.GET)
    public @ResponseBody List<Flight> getFlightByDepartureTime(@RequestParam LocalDateTime startTime, @RequestParam LocalDateTime endTime) {
        return null;
    }
}
