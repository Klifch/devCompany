package com.programming3.devcompany.domain;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "developer")
public class Developer {

//    private final Logger logger = LoggerFactory.getLogger(Developer.class);
    private static final AtomicInteger uniq_counter = new AtomicInteger();

    // Basic
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @Column(name = "hire_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endOfContract;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Integer id;


    // Connections
    @ManyToMany
    @JoinTable(
            name="developer_project",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
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

    public Developer(String firstName, String lastName, Integer age, Double salary, LocalDate endOfContract, Position position, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
        this.position = position;
        this.endOfContract = endOfContract;

        this.projects = new ArrayList<>();
        this.id = id;
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

    public void setEndOfContract(LocalDate endOfContract) {
        this.endOfContract = endOfContract;
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
//        logger.info("Starting {} to LocalDate conversion ...", localDate);
        try {
            LocalDate date = LocalDate.parse(localDate);
//            logger.info("Conversion to LocalDate successful ...");

            if (date.isAfter(LocalDate.now())) {
                this.endOfContract = date;
            } else {
                // logg error
//                logger.warn("Invalid date received as an input!");
            }

        } catch (DateTimeParseException e) {
            // logg error
//            logger.error("Couldn't parse String to LocalDate", e);
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
