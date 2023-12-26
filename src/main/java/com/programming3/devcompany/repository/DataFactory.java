package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Project;

import java.util.ArrayList;
import java.util.List;


public class DataFactory {
    public static List<Developer> listOfDevelopers = new ArrayList<>();
    public static List<Project> listOfProjects = new ArrayList<>();


    public static void seed() {
        Project springNew = new Project("Spring2", 100500, 9101);
        Project javaNew = new Project("java40", 13330, 9091);
        Project pythonNew = new Project("python4", 10, 10982);
        Project javaCrabbed = new Project("javaCrab", 100000, 23421);

        listOfProjects.add(springNew);
        listOfProjects.add(javaCrabbed);
        listOfProjects.add(javaNew);
        listOfProjects.add(pythonNew);

        Developer ben = new Developer("Ben", 20, 1, 2500d, "2030-03-02");
        Developer oleksii = new Developer("oleksii", 22, 2, 5500d, "2027-10-09");
        Developer anna = new Developer("Anna", 22, 3, 2700d, "2023-10-12");
        Developer leo = new Developer("Leo", 19, 4, 2500d, "2024-07-10");

        listOfDevelopers.add(oleksii);
        listOfDevelopers.add(ben);
        listOfDevelopers.add(anna);
        listOfDevelopers.add(leo);

        ben.assignToProject(javaCrabbed);
        ben.assignToProject(pythonNew);

        leo.assignToProject(javaCrabbed);
        leo.assignToProject(pythonNew);
        leo.assignToProject(javaNew);

        anna.assignToProject(springNew);

        oleksii.assignToProject(springNew);
        oleksii.assignToProject(javaNew);
        oleksii.assignToProject(pythonNew);

    }
}
