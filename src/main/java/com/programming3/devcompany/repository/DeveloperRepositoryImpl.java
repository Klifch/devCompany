package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("list")
public class DeveloperRepositoryImpl implements DeveloperRepository {

    private Logger logger = LoggerFactory.getLogger(DeveloperRepositoryImpl.class);
    private List<Developer> developers = new ArrayList<>();

    @Override
    public void addDeveloper(Developer developer) {
        logger.info("Adding developer {} to the DB ...", developer);
        if (!developers.contains(developer)) {
            developers.add(developer);
            logger.info("Successfully added Developer to the DB!");
        } else {
            logger.info("Developer already exists in DB");
        }
    }

    @Override
    public List<Developer> getAllDevelopers() {
        logger.info("Taking all developers from the DB ...");
        return developers;
    }

    @Override
    public Developer findById(int developerId) {
        return developers.stream()
                .filter(developer -> developer.getId().equals(developerId))
                .findFirst()
                .orElse(null);
    }
}
