package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Position;

import java.util.List;

public interface DeveloperRepository {

    void addDeveloper(Developer developer);

    List<Developer> getAllDevelopers();
}
