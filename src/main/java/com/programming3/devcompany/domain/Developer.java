package com.programming3.devcompany.domain;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

enum Position {
    Trainee, Junior, Middle, Senior, Lead
}

public class Developer {
    // Basic
    private String name;
    private Integer age;
    private Integer id;
    private Double salary;
    private LocalDate endOfContract;

    // Connections
    private List<Project> projects;
    private Position position;

    public Developer(String name, Integer age, Integer id, Double salary, String localDate) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.salary = salary;
        this.endOfContract = LocalDate.parse(localDate);
        this.projects = new ArrayList<>();
    }

    public void assignToProject(Project project) {
        if (!projects.contains(project)) {
            projects.add(project);
            project.addDeveloper(this);
        }
    }

    private String getProjectNamesInvolved() {
        StringBuilder projectString = new StringBuilder();
        projects.forEach(project -> projectString.append(project.getProjectName()).append(" "));
        return projectString.toString();
    }

    public String getName() {
        return name;
    }

    // Finish toString for all attributes
    @Override
    public String toString() {
        return String.format(
                "Developer %s, %d, developer id - %d, projects - %s, salary - %f, contract till - %s",
                name,
                age,
                id,
                getProjectNamesInvolved(),
                salary,
                endOfContract.toString()
        );
    }
}
