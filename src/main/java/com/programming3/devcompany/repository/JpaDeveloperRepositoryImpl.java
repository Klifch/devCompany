package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Developer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("jpa")
public class JpaDeveloperRepositoryImpl implements DeveloperRepository {

    private EntityManager entityManager;

    public JpaDeveloperRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void addDeveloper(Developer developer) {
        entityManager.merge(developer);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Developer developer = entityManager.find(Developer.class, id); // second option how to make findById()
        if (developer != null) {
            entityManager.remove(developer);
        }
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return entityManager.createQuery("select d from Developer d", Developer.class).getResultList();
    }

    @Override
    public Developer findById(int developerId) {
        try {
            return entityManager.createQuery("SELECT d from Developer d WHERE d.id = :developerId", Developer.class).setParameter("developerId", developerId).getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            return null;
        }
    }
}
