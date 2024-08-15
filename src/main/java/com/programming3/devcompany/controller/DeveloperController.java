package com.programming3.devcompany.controller;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Position;
import com.programming3.devcompany.presentation.viewmodel.DeveloperViewModel;
import com.programming3.devcompany.presentation.viewmodel.SearchViewModel;
import com.programming3.devcompany.presentation.viewmodel.SortViewModel;
import com.programming3.devcompany.service.DeveloperService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/developers")
public class DeveloperController {

    private Logger logger = LoggerFactory.getLogger(DeveloperController.class);
    private final DeveloperService developerService;

    @Value("${valuesOptions}")
    private List<String> valueOptions;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        logger.info("Creating new DeveloperService from Controller");
        this.developerService = developerService;
    }

    // adding initbinder preprocessor for trimming input strings
    // it will remove leading and trailing whitespaces
    // called for every request
    // https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-initbinder.html
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    // mapping for "/show" to show all developers
    @GetMapping("/show")
    public String showDevelopers(Model model) {
        logger.info("Received request /developers/show");

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

    @GetMapping("/showDetails")
    public String showDetailsFor(@RequestParam("developerId") Integer id, Model model) {
        logger.info("Received request /developers/showDetails/{}", id);

        // Retrieve the developer by id from the service
        logger.info("Calling developer Service to get developer details for ID {}", id);
        Developer developer = developerService.getOneById(id);

        // Add the developer to the model
        logger.info("Adding developer details to the model: {}", developer);
        model.addAttribute("developer", developer);

        // Return the details view
        logger.info("Presenting view 'developers/developer-details.html'");
        return "developers/developer-details";
    }


    // show form for adding new developer
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        logger.info("Received request /developers/showFormForAdd");

        // Create new employee
        // I can change this code for other constructor, but why?
        logger.info("Creating new developer ViewModel ...");
        DeveloperViewModel developerViewModel = new DeveloperViewModel();
//        Developer developer = new Developer();

        // add empty developer to the form
        logger.info("Adding new developerViewModel to the model");
        model.addAttribute("developerViewModel", developerViewModel);

        // add list of positions to the model
        logger.info("Adding all values of Position enum to the model");
        model.addAttribute("positions", Position.values());

        // return view
        logger.info("Presenting view 'developers/developers-form.html'");
        return "developers/developers-form";
    }

    @GetMapping("/showSortSalaryForm")
    public String showSortSalaryForm(Model model) {
        logger.info("Received request /developers/showFormForAdd");

        logger.info("Adding SortViewModel to the Model");
        model.addAttribute("sortViewModel", new SortViewModel());

        logger.info("Adding options to the Model");
        model.addAttribute("valueOptions", valueOptions);

        return "developers/developers-sort-salary";
    }

    @PostMapping("/compileSort")
    public String compileSort(
            Model model,
            @Valid @ModelAttribute("sortViewModel") SortViewModel sortVM,
            BindingResult bindingResult
    ) {
        logger.info("Checking for errors ...");
        if (bindingResult.hasErrors()) {
            logger.warn("Error found! Routing to form ...");
            logger.warn("Binding result: " + bindingResult.toString());
            model.addAttribute("valueOptions", valueOptions);
            return "developers/developers-sort-salary";
        }

        logger.info("Received post request /developers/compileSort with SortViewModel {}", sortVM);
        List<Developer> developers;
        if (sortVM.getOption().equals("Higher")) {
            developers = developerService.findByWithSalaryHigher(sortVM.getAmount());
        } else if (sortVM.getOption().equals("Lower")) {
            developers = developerService.findByWithSalaryLower(sortVM.getAmount());
        } else {
            return "redirect:/developers/show";
        }

        model.addAttribute("developers", developers);
        return "developers/show-developers";
    }


    @GetMapping("/showSearchNameForm")
    public String showFindByNameForm(Model model) {
        logger.info("Received request /developers/findByNameForm");

        logger.info("Adding searchNameViewModel to the Model");
        model.addAttribute("searchNameVM", new SearchViewModel());

        return "developers/developers-search-name";
    }

    @PostMapping("/searchByName")
    public String compileSort(
            Model model,
            @Valid @ModelAttribute("searchNameVM") SearchViewModel searchVM,
            BindingResult bindingResult
    ) {
        logger.info("Checking for errors ...");
        if (bindingResult.hasErrors()) {
            logger.warn("Error found! Routing to form ...");
            logger.warn("Binding result: " + bindingResult.toString());
            return "developers/developers-search-name";
        }

        logger.info("Received post request /developers/searchByName with SearchViewModel {}", searchVM);
        List<Developer> developers = developerService.findWithNameOrSurname(searchVM.getNameOrSurname());

        model.addAttribute("developers", developers);
        return "developers/show-developers";
    }


    @PostMapping("/save")
    public String saveDeveloper(
            Model model,
            @Valid @ModelAttribute("developerViewModel") DeveloperViewModel developerVM,
            BindingResult bindingResult
    ) {
        logger.info("Checking for errors ...");
        if (bindingResult.hasErrors()) {
            logger.warn("Error found! Routing to form ...");
            logger.warn("Binding result: " + bindingResult.toString());
            model.addAttribute("positions", Position.values());
            return "developers/developers-form";
        }

        logger.info("Received post request /employees/save with DeveloperViewModel {}", developerVM);

        // save received developer
        logger.info("Calling Developer service from controller to add new developer {}", developerVM);
        developerService.createDeveloper(new Developer(
                developerVM.getFirstName(),
                developerVM.getLastName(),
                developerVM.getAge(),
                developerVM.getSalary(),
                developerVM.getEndOfContract(),
                developerVM.getPosition()
                )
        );
//        developerService.createDeveloper(developer);

        // redirect to the list of all developers
        logger.info("Redirecting to /developers/show");
        return "redirect:/developers/show";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("developerId") Integer id) {

        developerService.deleteById(id);

        return "redirect:/developers/show";
    }
}
