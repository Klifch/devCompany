package com.programming3.devcompany.domain;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Developer {

    private static final AtomicInteger uniq_counter = new AtomicInteger();

    // Basic
    private String name;
    private Integer age;
    private Double salary;
    private LocalDate endOfContract;
    private final int id;


    // Connections
    private List<Project> projects;
    private Position position;

    public Developer(String name, Integer age, Double salary, String localDate, Position position) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.position = position;
        checkDate(localDate);
        this.projects = new ArrayList<>();
        id = uniq_counter.incrementAndGet();
    }

    // it's not bidirectional for now
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

    public List<Project> getProjects() {
        return projects;
    }

    public Double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    private void checkDate(String localDate) {
        try {
            LocalDate date = LocalDate.parse(localDate);

            if (date.isAfter(LocalDate.now())) {
                this.endOfContract = date;
            } else {
                // logg error
            }

        } catch (DateTimeParseException e) {
            // logg error
        }
    }

    // Finish toString for all attributes
    @Override
    public String toString() {
        return String.format(
                "Developer %s, %s, %d, developer id - %d, projects - %s, salary - %f, contract till - %s",
                name,
                position,
                age,
                id,
                getProjectNamesInvolved(),
                salary,
                endOfContract != null ? endOfContract.toString() : "not assigned"
        );
    }
}
