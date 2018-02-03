package hu.myprojects.flighttracker.controller;

import hu.myprojects.flighttracker.dao.FlightRepository;
import hu.myprojects.flighttracker.domain.Flight;
import hu.myprojects.flighttracker.exception.BusinessException;
import hu.myprojects.flighttracker.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository repository;

    @RequestMapping(value = "/flight", method = RequestMethod.POST)
    public void insertFlight(
            @RequestBody Flight flight){

        flightService.insertFlight(flight);
    }

    @RequestMapping(value = "/flight/update", method = RequestMethod.POST)
    public void updateFlight(
            @RequestBody Flight flight) {

        flightService.updateFlight(flight);
    }

    @RequestMapping(value = "/flight/delete", method = RequestMethod.DELETE)
    public void deleteFlight(
            @RequestParam Long id) {

        flightService.deleteFlight(id);
    }

    @RequestMapping(value = "/flight/list", method = RequestMethod.GET)
    public @ResponseBody Iterable<Flight> listFlight() {

        return repository.findAll();
    }

    @RequestMapping(value = "/flight/get/interval", method = RequestMethod.GET)
    public @ResponseBody List<Flight> getFlightByDepartureTime(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {

        if (startTime == null) {
            startTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        }
        if (endTime == null) {
            endTime = LocalDateTime.of(9999, 1, 1, 0, 0);
        }
        return repository.findByDepartureTimeBetween(startTime, endTime);
    }
}
