package com.programming3.devcompany.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Project {

    private static final AtomicInteger uniq_counter = new AtomicInteger();

    // Basic
    private String projectName;
    private Double projectBudget;
    private final Integer projectId;

    // Connections
    private Developer headOfProject;
    private Office projectOffice;
    private List<Developer> developers;


    public Project() {
        projectId = uniq_counter.incrementAndGet();
    }

    public Project(String projectName, Double projectBudget) {
        this.projectName = projectName;
        this.projectBudget = projectBudget;

        this.developers = new ArrayList<>();
        projectId = uniq_counter.incrementAndGet();
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public Double getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(Double projectBudget) {
        this.projectBudget = projectBudget;
    }

    public Developer getHeadOfProject() {
        return headOfProject;
    }

    public void setHeadOfProject(Developer headOfProject) {
        this.headOfProject = headOfProject;
    }

    public Office getProjectOffice() {
        return projectOffice;
    }

    public void setProjectOffice(Office projectOffice) {
        if (!projectOffice.equals(this.projectOffice)) {
            this.projectOffice = projectOffice;
            projectOffice.addProjectToOffice(this);
        }
    }

    public Integer getProjectId() {
        return projectId;
    }


    public String getDevelopersAsString() {
        StringBuilder developersString = new StringBuilder();
        for (Developer developer : developers) {
            developersString.append(developer.getFirstName()).append(" ");
        }
        return developersString.toString();
    }

    // it's not bidirectional for now
    public void addDeveloper(Developer developer) {
        if (!developers.contains(developer)) {
            developers.add(developer);
            developer.assignToProject(this);
        }
    }
    //  TODO: add auto delete later

    // ...

    // ...


    @Override
    public String toString() {
        return String.format(
                "Project %s with budget %s and id %d\nHead - %s, located at %s,%s",
                projectName,
                projectBudget,
                projectId,
                headOfProject != null ? headOfProject.getFirstName() : "not assigned",
                projectOffice != null ? projectOffice.getName() : "not assigned",
                projectOffice != null ? projectOffice.getLocation() : "not assigned"
        );
    }
}

