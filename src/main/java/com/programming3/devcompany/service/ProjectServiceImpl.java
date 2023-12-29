package com.programming3.devcompany.service;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Project;
import com.programming3.devcompany.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        logger.info("Creating new projectRepository ... ");
        this.projectRepository = projectRepository;
    }

    @Override
    public void createProject(Project project) {
        logger.info("Calling Project repository from Service to add new Project {}", project);
        projectRepository.addProject(project);
    }

    @Override
    public void createProject(String projectName, Integer projectBudget) {
        logger.info("Creating new project using arguments ... ");
        logger.info("Calling Project repository from Service to add new Project");
        projectRepository.addProject(new Project(
                projectName,
                projectBudget
        ));
    }

    @Override
    public List<Project> getAllProjects() {
        logger.info("Calling Project repository from Service to get all Projects");
        return projectRepository.getAllProjects();
    }

    @Override
    public Project getProjectById(Integer id) {
        logger.info("Filtering projects to find one with ID {} ... ", id);
        return projectRepository.getAllProjects()
                .stream()
                .filter(project -> project.getProjectId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Project> getProjectsForDeveloper(Developer developer) {
        logger.info("Calling Project repository from Service to find all Projects for Developer {}", developer);
        return projectRepository.getAllProjects()
                .stream()
                .filter(project -> project.getDevelopers().contains(developer))
                .collect(Collectors.toList());
    }
}
