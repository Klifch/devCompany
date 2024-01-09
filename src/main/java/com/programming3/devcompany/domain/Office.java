package com.programming3.devcompany.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "office")
public class Office {
    // Basic

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String location;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer officeId;

    // Connections
//    @ManyToMany
//    @JoinTable(
//            name = "office_project",
//            joinColumns = @JoinColumn(name = "office_id"),
//            inverseJoinColumns = @JoinColumn(name = "project_id")
//    )
//    private List<Project> projects;

    @OneToMany
    @JoinTable(
            name = "office_project",
            joinColumns = @JoinColumn(name = "office_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

    public Office(String name, String location, Integer officeId) {
        this.name = name;
        this.location = location;
        this.officeId = officeId;
        this.projects = new ArrayList<>();
    }

    public Office() {

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
