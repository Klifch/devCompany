package com.programming3.devcompany.domain;

import java.util.ArrayList;
import java.util.List;

public class Office {
    // Basic
    private String name;
    private String location;
    private Integer officeId;

    // Connections
    private List<Project> projects;

    public Office(String name, String location, Integer officeId) {
        this.name = name;
        this.location = location;
        this.officeId = officeId;
        this.projects = new ArrayList<>();
    }

    private String getProjectNames() {
        StringBuilder projectString = new StringBuilder();
        projects.forEach(project -> projectString.append(project.getProjectName()).append(" "));
        return projectString.toString();
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void addProjectToOffice(Project project) {
        if (!projects.contains(project)) {
            projects.add(project);
            project.setProjectOffice(this);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Office %s that located at %s and has an id number %d, projects: %s",
                name,
                location,
                officeId,
                getProjectNames()
        );
    }

}
