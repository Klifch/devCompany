package com.programming3.devcompany.service;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Position;
import com.programming3.devcompany.domain.Project;
import com.programming3.devcompany.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    @Autowired
    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public void createDeveloper(Developer developer) {
        developerRepository.addDeveloper(developer);
    }

    @Override
    public void createDeveloper(String name, Integer age, Double salary, String localDate, Position position) {
        developerRepository.addDeveloper(new Developer(
                name,
                age,
                salary,
                localDate,
                position
        ));
    }

    @Override
    public Developer getOneById(Integer id) {
        return developerRepository.getAllDevelopers()
                .stream()
                .filter(developer -> developer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Developer> getAll() {
        return developerRepository.getAllDevelopers();
    }

    @Override
    public List<Developer> findAllWithSalary(Double salary, int direction) {
        switch (direction) {

            case 2 -> {
                return developerRepository.getAllDevelopers()
                        .stream()
                        .filter(developer -> developer.getSalary() < salary)
                        .collect(Collectors.toList());
            }
            case 1 -> {
                return developerRepository.getAllDevelopers()
                        .stream()
                        .filter(developer -> developer.getSalary() > salary)
                        .collect(Collectors.toList());
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public List<Developer> getAllForProject(Project project) {
        return developerRepository.getAllDevelopers()
                .stream()
                .filter(developer -> developer.getProjects().contains(project))
                .collect(Collectors.toList());
    }
}
