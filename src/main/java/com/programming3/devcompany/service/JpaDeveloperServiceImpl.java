package com.programming3.devcompany.service;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Position;
import com.programming3.devcompany.domain.Project;
import com.programming3.devcompany.exception.DeveloperNotFoundException;
import com.programming3.devcompany.repository.JpaRepDeveloperRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("jparep")
@Primary
public class JpaDeveloperServiceImpl implements DeveloperService {

    Logger logger = LoggerFactory.getLogger(JpaDeveloperServiceImpl.class);

    private JpaRepDeveloperRepository jpaRepDeveloperRepository;

    public JpaDeveloperServiceImpl(JpaRepDeveloperRepository jpaRepDeveloperRepository) {
        logger.warn("YOOOOO JPAREP IN DA BUILDING!!!!!!!!!");
        this.jpaRepDeveloperRepository = jpaRepDeveloperRepository;
    }

    @Override
    public void createDeveloper(Developer developer) {
        logger.info("JPA CALLING JPAREP REPOSITORY FOR CREATIN A NEW DEVELOPER");
        jpaRepDeveloperRepository.save(developer);
    }

    @Override
    public void createDeveloper(String firstName, String lastName, Integer age, Double salary, String localDate, Position position) {

    }

    @Override
    public Developer getOneById(Integer id) {
        logger.warn("JPA REP WILL FIND THIS DEVELOPER {} !!!!!!!!!", id);
        return jpaRepDeveloperRepository.findById(id).orElseThrow(
                () -> new DeveloperNotFoundException(String.format("Developer with id %s not found", id))
        );
    }

    @Override
    public List<Developer> getAll() {
        logger.info("YOOOO JPA REP SRVICE RETURNING ALL DEVELOPERS!!!!!!");
        return jpaRepDeveloperRepository.findAll();
    }

    @Override
    public List<Developer> findAllWithSalary(Double salary, int option) {
        return null;
    }

    @Override
    public List<Developer> getAllForProject(Project project) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        logger.warn("JPA REP WILL DESTROY THIS PUPPY id - {}", id);
        jpaRepDeveloperRepository.deleteById(id);
    }

    @Override
    public List<Developer> findByPosition(Position position) {
        List<Developer> developers = jpaRepDeveloperRepository.findAllByPosition(position);

        if (developers.isEmpty()) {
            logger.error("Developer with position {} not found", position);
            throw new DeveloperNotFoundException(String.format("Developer with position %s not found", position));
        }

        return developers;
    }

    @Override
    public List<Developer> findByWithSalaryLower(Double salary) {
        List<Developer> developers = jpaRepDeveloperRepository.findAllBySalaryBefore(salary);

        if (developers.isEmpty()) {
            logger.error("Developer with salary lower than {} not found", salary);
            throw new DeveloperNotFoundException(String.format("Developer salary lower than %s not found", salary));
        }

        return developers;
    }

    @Override
    public List<Developer> findByWithSalaryHigher(Double salary) {
        List<Developer> developers = jpaRepDeveloperRepository.findAllBySalaryAfter(salary);

        if (developers.isEmpty()) {
            logger.error("Developer with salary higher than {} not found", salary);
            throw new DeveloperNotFoundException(String.format("Developer with salary higher than %s not found", salary));
        }

        return developers;
    }

    @Override
    public List<Developer> findWithNameOrSurname(String nameOrSurname) {
        List<Developer> developers = jpaRepDeveloperRepository.findByFirstNameOrLastNameStartingWith(nameOrSurname);

        if (developers.isEmpty()) {
            logger.error("Developer with name {} not found", nameOrSurname);
            throw new DeveloperNotFoundException(String.format("Developer with name %s not found", nameOrSurname));
        }

        return developers;
    }
}
