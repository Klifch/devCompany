package com.programming3.devcompany.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Developer {

    private Logger logger = LoggerFactory.getLogger(Developer.class);
    private static final AtomicInteger uniq_counter = new AtomicInteger();

    // Basic
    private String firstName;
    private String lastName;
    private Integer age;
    private Double salary;
    private LocalDate endOfContract;
    private final Integer id;


    // Connections
    private List<Project> projects;
    private Position position;

    public Developer() {
        id = uniq_counter.incrementAndGet();
    }

    public Developer(String firstName, String lastName, Integer age, Double salary, String localDate, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
        this.position = position;
        checkDate(localDate);
        this.projects = new ArrayList<>();
        id = uniq_counter.incrementAndGet();
    }

    public Developer(String firstName, String lastName, Integer age, Double salary, LocalDate endOfContract, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
        this.position = position;
        this.endOfContract = endOfContract;

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

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getEndOfContract() {
        return endOfContract;
    }

    public void setEndOfContract(String endOfContract) {
        checkDate(endOfContract);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getId() {
        return id;
    }


    // TODO: old conversion stuff - clean later
    private void checkDate(String localDate) {
        logger.info("Starting {} to LocalDate conversion ...", localDate);
        try {
            LocalDate date = LocalDate.parse(localDate);
            logger.info("Conversion to LocalDate successful ...");

            if (date.isAfter(LocalDate.now())) {
                this.endOfContract = date;
            } else {
                // logg error
                logger.warn("Invalid date received as an input!");
            }

        } catch (DateTimeParseException e) {
            // logg error
            logger.error("Couldn't parse String to LocalDate", e);
        }
    }

    // TODO: Finish toString for all attributes
    @Override
    public String toString() {
        return String.format(
                "Developer %s %s, %s, %d, developer id - %d, projects - %s, salary - %f, contract till - %s",
                firstName,
                lastName,
                position,
                age,
                id,
                getProjectNamesInvolved(),
                salary,
                endOfContract != null ? endOfContract.toString() : "not assigned"
        );
    }
}
