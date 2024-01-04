package com.programming3.devcompany.service;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Position;
import com.programming3.devcompany.domain.Project;

import java.util.List;

public interface DeveloperService {

    void createDeveloper(Developer developer);

    void createDeveloper(String firstName,
                         String lastName,
                         Integer age,
                         Double salary,
                         String localDate,
                         Position position
    );

    Developer getOneById(Integer id);

    List<Developer> getAll();

    List<Developer> findAllWithSalary(Double salary, int option);

    List<Developer> getAllForProject(Project project);

    void deleteById(Integer id);
}
