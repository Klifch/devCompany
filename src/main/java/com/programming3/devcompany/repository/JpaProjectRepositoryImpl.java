package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("jpa")
public class JpaProjectRepositoryImpl implements ProjectRepository {

    private EntityManager entityManager;

    public JpaProjectRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void addProject(Project project) {
        entityManager.merge(project);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Project project = entityManager.find(Project.class, id); // second option how to make findById()
        if (project != null) {
            entityManager.remove(project);
        }
    }

    @Override
    public List<Project> getAllProjects() {
        return entityManager.createQuery("select p from Project p", Project.class).getResultList();
    }

    @Override
    public Project getOneById(Integer id) {
        try {
            return entityManager.createQuery("SELECT p from Project p WHERE p.id = :id", Project.class).setParameter("id", id).getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            return null;
        }
    }
}
