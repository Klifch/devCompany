package com.programming3.devcompany.controller;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Position;
import com.programming3.devcompany.domain.Project;
import com.programming3.devcompany.presentation.viewmodel.ProjectViewModel;
import com.programming3.devcompany.presentation.viewmodel.SortViewModel;
import com.programming3.devcompany.service.ProjectService;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private Logger logger = LoggerFactory.getLogger(ProjectController.class);
    private final ProjectService projectService;

    @Value("${valuesOptions}")
    private List<String> valueOptions;

    @Autowired
    public ProjectController(ProjectService projectService) {
        logger.info("Creating new ProjectService from Controller");
        this.projectService = projectService;
    }

    // adding initbinder preprocessor for trimming input strings
    // it will remove leading and trailing whitespaces
    // called for every request
    // DOC: https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-initbinder.html
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/show")
    public String showProjects(Model model) {
        logger.info("Received request /projects/show");

        // get all projects from DB
        logger.info("Calling project Service to get All projects ... ");
        List<Project> projects = projectService.getAllProjects();

        // add projects to the Model
        logger.info("Adding received projects to the model ...");
        model.addAttribute("projects", projects);

        logger.info("Presenting view 'projects/show-projects.html'");
        return "projects/show-projects";
    }

    @GetMapping("/showDetails")
    public String showDetailsFor(@RequestParam("projectId") Integer id, Model model) {
        logger.info("Received request /projects/showDetails/{}", id);

        // Retrieve the developer by id from the service
        logger.info("Calling project Service to get project details for ID {}", id);
        Project project = projectService.getProjectById(id);

        // Add the developer to the model
        logger.info("Adding project details to the model");
        model.addAttribute("project", project);

        // Return the details view
        logger.info("Presenting view 'projects/project-details.html'");
        return "projects/project-details";
    }

    // show form for adding new developer
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        logger.info("Received request /projects/showFormForAdd");

        // Create new project
        // I can change this code for other constructor, but why?
        logger.info("Creating new project ...");
        ProjectViewModel projectViewModel = new ProjectViewModel();

        // add empty developer to the form
        logger.info("Adding new project to the model");
        model.addAttribute("projectViewModel", projectViewModel);

        // return view
        logger.info("Presenting view 'projects/projects-form.html'");
        return "projects/projects-form";
    }

    @GetMapping("/showSortBudgetForm")
    public String showSortBudgetForm(Model model) {
        logger.info("Received request /projects/showSortBudgetForm");

        logger.info("Adding SortViewModel to the Model");
        model.addAttribute("sortViewModel", new SortViewModel());

        logger.info("Adding options to the Model");
        model.addAttribute("valueOptions", valueOptions);

        return "projects/projects-sort-budget";
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
            return "projects/projects-sort-budget";
        }

        logger.info("Received post request /projects/compileSort with SortViewModel params: {} {}", sortVM.getAmount(), sortVM.getOption());

        List<Project> projects;
        if (sortVM.getOption().equals("Higher")) {
            logger.info("Getting projects with budget Higher than {}", sortVM.getAmount());
            projects = new ArrayList<>(projectService.findAllByBudgetHigher(sortVM.getAmount()));
        } else if (sortVM.getOption().equals("Lower")) {
            logger.info("Getting projects with budget Lower than {}", sortVM.getAmount());
            projects = new ArrayList<>(projectService.findAllByBudgetLower(sortVM.getAmount()));
        } else {
            return "redirect:/projects/show";
        }

        logger.info("Found {} projects", projects.size());

        model.addAttribute("projects", projects);
        return "projects/show-projects";
    }

    @PostMapping("/save")
    public String saveDeveloper(
            @Valid @ModelAttribute("projectViewModel") ProjectViewModel projectViewModel,
            BindingResult bindingResult
    ) {
        logger.info("Checking for errors ...");
        if (bindingResult.hasErrors()) {
            logger.warn("Error found! Routing to form ...");
            logger.warn("Binding result: " + bindingResult.toString());
            return "projects/projects-form";
        }

        logger.info("Received post request /projects/save with ProjectViewModel {}", projectViewModel);

        // save received developer
        logger.info("Calling Project service from controller to add new project {}", projectViewModel);
        projectService.createProject(new Project(
                projectViewModel.getProjectName(),
                projectViewModel.getProjectBudget()
                )
        );

        // redirect to the list of all developers
        logger.info("Redirecting to /projects/show");
        return "redirect:/projects/show";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("projectId") Integer id) {

        projectService.deleteById(id);

        return "redirect:/projects/show";
    }

    @GetMapping("/trigger-sql-exception")
    public String triggerSqlException() throws SQLException {

        projectService.triggerSQLException();

        return "redirect:/projects/show";
    }
}
