package hu.myprojects.flighttracker.controller;

import hu.myprojects.flighttracker.dao.FlightRepository;
import hu.myprojects.flighttracker.domain.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private FlightRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listFlight(Model model) {

        List<Flight> flights = repository.findAll();
        model.addAttribute("flights", flights);
        return "listFlight.html";
    }
}
