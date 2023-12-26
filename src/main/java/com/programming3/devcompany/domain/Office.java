package com.programming3.devcompany.domain;

import java.util.List;

public class Office {
    // Basic
    private String name;
    private String location;
    private Integer officeId;

    // Connections
    private List<Project> projects;

    private String getProjectNames() {
        StringBuilder projectString = new StringBuilder();
        projects.forEach(project -> projectString.append(project.getProjectName()));
        return projectString.toString();
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
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
