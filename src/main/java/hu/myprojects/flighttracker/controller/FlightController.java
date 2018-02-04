package hu.myprojects.flighttracker.controller;

import hu.myprojects.flighttracker.dao.FlightRepository;
import hu.myprojects.flighttracker.domain.Flight;
import hu.myprojects.flighttracker.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository repository;

    @RequestMapping(value = "/flight/insert", method = RequestMethod.POST)
    public String insertFlight( @Valid @ModelAttribute Flight flight, BindingResult errors, Model model) {

        if (!errors.hasErrors()) {
            flightService.insertFlight(flight);
            List<Flight> flights = repository.findAll();
            model.addAttribute("flights",flights);
        }
        return ((errors.hasErrors()) ? "insertFlight.html" : "listFlight.html");
    }

    @RequestMapping(value = "/flight/insert", method = RequestMethod.GET)
    public String insertFlight(Model model) {

        model.addAttribute("flight", new Flight());
        return "insertFlight.html";
    }

    @RequestMapping(value = "/flight/update", method = RequestMethod.POST)
    public String updateFlight( @Valid @ModelAttribute Flight flight, BindingResult errors, Model model) {

        if (!errors.hasErrors()) {
            flightService.updateFlight(flight);
            List<Flight> flights = repository.findAll();
            model.addAttribute("flights",flights);
        }
        return ((errors.hasErrors()) ? "updateFlight.html" : "listFlight.html");
    }

    @RequestMapping(value = "/flight/update", method = RequestMethod.GET)
    public String updateFlight(Model model) {

        model.addAttribute("flight", new Flight());
        return "updateFlight.html";
    }

    @RequestMapping(value = "/flight/delete/{id}", method = RequestMethod.POST)
    public String deleteFlight(@PathVariable Long id, Model model) {

        flightService.deleteFlight(id);

        List<Flight> flights = repository.findAll();
        model.addAttribute("flights",flights);
        return "listFlight.html";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listFlight(Model model) {

        List<Flight> flights = repository.findAll();
        model.addAttribute("flights", flights);
        return "listFlight.html";
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
