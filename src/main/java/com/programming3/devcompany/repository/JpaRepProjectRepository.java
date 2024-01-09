package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaRepProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findAllByProjectBudgetAfter(Double budget);

    List<Project> findAllByProjectBudgetBefore(Double budget);
}
