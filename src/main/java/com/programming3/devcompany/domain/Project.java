package com.programming3.devcompany.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "project")
public class Project {

    private static final AtomicInteger uniq_counter = new AtomicInteger();

    // Basic
    @Column(name = "name", nullable = false)
    private String projectName;


    @Column(name = "budget", nullable = false)
    private Double projectBudget;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Integer projectId;

    // Connections
    @Transient
    private Developer headOfProject;


//    @Transient
    @ManyToOne
    @JoinTable(
        name = "office_project",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "office_id")
    )
    private Office projectOffice;

    @ManyToMany
    @JoinTable(
            name="developer_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
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

    public Project(String projectName, Double projectBudget, Integer projectId) {
        this.projectName = projectName;
        this.projectBudget = projectBudget;

        this.developers = new ArrayList<>();
        this.projectId = projectId;
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
        this.projectOffice = projectOffice;
//        if (!projectOffice.equals(this.projectOffice)) {
//            this.projectOffice = projectOffice;
//            projectOffice.addProjectToOffice(this);
//        }
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

