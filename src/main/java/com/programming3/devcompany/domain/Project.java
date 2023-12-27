package com.programming3.devcompany.domain;

import java.util.ArrayList;
import java.util.List;

public class Project {
    // Basic
    private String projectName;
    private Integer projectBudget;
    private Integer projectId;

    // Connections
    private Developer headOfProject;
    private Office projectOffice;
    private List<Developer> developers;

    public Project(String projectName, Integer projectBudget, Integer projectId) {
        this.projectName = projectName;
        this.projectBudget = projectBudget;
        this.projectId = projectId;
        this.developers = new ArrayList<>();
    }

    // it's not bidirectional for now
    public void addDeveloper(Developer developer) {
        if (!developers.contains(developer)) {
            developers.add(developer);
            developer.assignToProject(this);
        }
    }

    public String getProjectName() {
        return projectName;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public String getDevelopersAsString() {
        StringBuilder developersString = new StringBuilder();
        for (Developer developer : developers) {
            developersString.append(developer.getName()).append(" ");
        }
        return developersString.toString();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectOffice(Office projectOffice) {
        if (!projectOffice.equals(this.projectOffice)) {
            this.projectOffice = projectOffice;
            projectOffice.addProjectToOffice(this);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Project %s with budget %s and id %d\nHead - %s, located at %s,%s",
                projectName,
                projectBudget,
                projectId,
                headOfProject != null ? headOfProject.getName() : "not assigned",
                projectOffice != null ? projectOffice.getName() : "not assigned",
                projectOffice != null ? projectOffice.getLocation() : "not assigned"
        );
    }
}

