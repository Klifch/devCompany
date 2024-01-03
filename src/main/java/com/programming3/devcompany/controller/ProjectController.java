package com.programming3.devcompany.controller;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Position;
import com.programming3.devcompany.domain.Project;
import com.programming3.devcompany.presentation.viewmodel.ProjectViewModel;
import com.programming3.devcompany.service.ProjectService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private Logger logger = LoggerFactory.getLogger(ProjectController.class);
    private final ProjectService projectService;

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
}
