package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaRepDeveloperRepository extends JpaRepository<Developer, Integer> {

    List<Developer> findAllBySalaryAfterAndSalaryBefore(Double after, Double before);

    List<Developer> findAllBySalaryAfter(Double after);

    List<Developer> findAllBySalaryBefore(Double before);

    List<Developer> findAllByPosition(Position position);

//    @Query("SELECT d FROM Developer d " +
//            "WHERE LOWER(d.firstName) " +
//            "LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(d.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
//    List<Developer> findByFirstNameOrLastNameStartingWith(@Param("searchTerm") String searchTerm);

    @Query("SELECT d FROM Developer d " +
            "WHERE LOWER(CONCAT(d.firstName, ' ', d.lastName)) " +
            "LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Developer> findByFirstNameOrLastNameStartingWith(@Param("searchTerm") String searchTerm);
}
