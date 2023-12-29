package com.programming3.devcompany.controller;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Position;
import com.programming3.devcompany.service.DeveloperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/developers")
public class DeveloperController {

    private Logger logger = LoggerFactory.getLogger(DeveloperController.class);
    private final DeveloperService developerService;

//    @Value("${positions}")
//    private List<String> positions;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        logger.info("Creating new DeveloperService from Controller");
        this.developerService = developerService;
    }

    // mapping for "/show" to show all developers
    @GetMapping("/show")
    public String showDevelopers(Model model) {
        logger.info("Received request /employees/show");

        // get all developers from DB
        logger.info("Calling developer Service to get All developers ... ");
        List<Developer> developers = developerService.getAll();

        // add developers to the Model
        logger.info("Adding received developers to the model ...");
        model.addAttribute("developers", developers);

        // return view
        logger.info("Presenting view 'developers/show-developers.html'");
        return "developers/show-developers";
    }

    // show form for adding new developer
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        logger.info("Received request /employees/showFormForAdd");

        // Create new employee
        // I can change this code for other constructor, but why?
        logger.info("Creating new developer ...");
        Developer developer = new Developer();

        // add empty developer to the form
        logger.info("Adding new developer to the model");
        model.addAttribute("developer", developer);

        // add list of positions to the model
        logger.info("Adding all values of Position enum to the model");
        model.addAttribute("positions", Position.values());

        // return view
        logger.info("Presenting view 'developers/developers-form.html'");
        return "developers/developers-form";
    }

    @PostMapping("/save")
    public String saveDeveloper(@ModelAttribute("developer") Developer developer) {
        logger.info("Received post request /employees/save with Developer {}", developer);

        // save received developer
        logger.info("Calling Developer service from controller to add new developer {}", developer);
        developerService.createDeveloper(developer);

        // redirect to the list of all developers
        logger.info("Redirecting to /developers/show");
        return "redirect:/developers/show";
    }
}
