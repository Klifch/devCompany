package com.programming3.devcompany.service;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Position;
import com.programming3.devcompany.domain.Project;
import com.programming3.devcompany.repository.DeveloperRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private Logger logger = LoggerFactory.getLogger(DeveloperServiceImpl.class);
    private final DeveloperRepository developerRepository;

    @Autowired
    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        logger.info("Creating new developerRepository ... ");
        this.developerRepository = developerRepository;
    }

    @Override
    public void createDeveloper(Developer developer) {
        logger.info("Calling Developer repository from Service to add new Developer {}", developer);
        developerRepository.addDeveloper(developer);
    }

    @Override
    public void createDeveloper(String firstName, String lastName, Integer age, Double salary, String localDate, Position position) {
        logger.info("Creating new developer using arguments ... ");
        logger.info("Calling Developer repository from Service to add new Developer ... ");
        developerRepository.addDeveloper(new Developer(
                firstName,
                lastName,
                age,
                salary,
                localDate,
                position
        ));
    }

    @Override
    public Developer getOneById(Integer id) {
        logger.info("Filtering developers to find one with ID {} ... ", id);
//        return developerRepository.getAllDevelopers()
//                .stream()
//                .filter(developer -> developer.getId().equals(id))
//                .findFirst()
//                .orElse(null);
        return developerRepository.findById(id);
    }

    @Override
    public List<Developer> getAll() {
        logger.info("Calling Developer repository from Service to get all Developers");
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
        logger.info("Calling Developer repository from Service to find all Developers for Project {}", project);
        return developerRepository.getAllDevelopers()
                .stream()
                .filter(developer -> developer.getProjects().contains(project))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        developerRepository.deleteById(id);
    }
}
