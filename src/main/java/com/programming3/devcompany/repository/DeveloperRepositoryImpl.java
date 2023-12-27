package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Position;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DeveloperRepositoryImpl implements DeveloperRepository {

    private List<Developer> developers = new ArrayList<>();

    @Override
    public void addDeveloper(Developer developer) {
        if (!developers.contains(developer)) {
            developers.add(developer);
        }
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return developers;
    }
}
