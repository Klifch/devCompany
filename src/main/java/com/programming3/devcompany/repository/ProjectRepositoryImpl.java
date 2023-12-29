package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    private Logger logger = LoggerFactory.getLogger(ProjectRepositoryImpl.class);
    private List<Project> projects = new ArrayList<>();

    @Override
    public void addProject(Project project) {
        logger.info("Adding project {} to the DB ...", project);
        if (!projects.contains(project)) {
            logger.info("Successfully added Project to the DB!");
            projects.add(project);
        } else {
            logger.info("Project already exists in DB");
        }
    }

    @Override
    public List<Project> getAllProjects() {
        logger.info("Taking all projects from the DB ...");
        return projects;
    }
}
