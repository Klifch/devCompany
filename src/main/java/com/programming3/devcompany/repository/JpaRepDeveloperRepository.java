package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaRepDeveloperRepository extends JpaRepository<Developer, Integer> {

    List<Developer> findAllBySalaryAfterAndSalaryBefore(Double after, Double before);

    List<Developer> findAllBySalaryAfter(Double after);

    List<Developer> findAllBySalaryBefore(Double before);

    List<Developer> findAllByPosition(Position position);
}
