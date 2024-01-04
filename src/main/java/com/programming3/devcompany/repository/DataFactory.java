package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Office;
import com.programming3.devcompany.domain.Position;
import com.programming3.devcompany.domain.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(1)
@Profile("list")
public class DataFactory implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(DataFactory.class);
    private final DeveloperRepository developerRepository;
    private final ProjectRepository projectRepository;
    private final OfficeRepository officeRepository;

    @Autowired
    public DataFactory(
            DeveloperRepository developerRepository,
            ProjectRepository projectRepository,
            OfficeRepository officeRepository
    ) {
        this.developerRepository = developerRepository;
        this.projectRepository = projectRepository;
        this.officeRepository = officeRepository;
    }

    public void seed() {
        logger.info("Seeding the DB with values ...");

        List<Developer> listOfDevelopers = new ArrayList<>();
        List<Project> listOfProjects = new ArrayList<>();
        List<Office> officeList = new ArrayList<>();

        Project springNew = new Project("Spring2", 100500d);
        Project javaNew = new Project("java40", 13330d);
        Project pythonNew = new Project("python4", 1000d);
        Project javaCrabbed = new Project("javaCrab", 100000d);

        listOfProjects.add(springNew);
        listOfProjects.add(javaCrabbed);
        listOfProjects.add(javaNew);
        listOfProjects.add(pythonNew);

        Developer ben = new Developer("Ben", "Brown", 20, 2500d, "2030-03-02", Position.SENIOR);
        Developer oleksii = new Developer("Oleksii", "Demydenko", 22, 5500d, "2027-10-09", Position.JUNIOR);
        Developer anna = new Developer("Anna", "Smith", 22, 2700d, "2023-10-12", Position.LEAD);
        Developer leo = new Developer("Leo", "Potters", 19, 2500d, "2024-07-10", Position.JUNIOR);

        listOfDevelopers.add(oleksii);
        listOfDevelopers.add(ben);
        listOfDevelopers.add(anna);
        listOfDevelopers.add(leo);

        Office gent = new Office("BrOffice", "Long ave. 14", 13);
        Office antwerp = new Office("Groen", "Nationalestraat 5", 55);

        officeList.add(gent);
        officeList.add(antwerp);


        ben.assignToProject(javaCrabbed);
        ben.assignToProject(pythonNew);

        leo.assignToProject(javaCrabbed);
        leo.assignToProject(pythonNew);
        leo.assignToProject(javaNew);

        anna.assignToProject(springNew);

        oleksii.assignToProject(springNew);
        oleksii.assignToProject(javaNew);
        oleksii.assignToProject(pythonNew);

        gent.addProjectToOffice(springNew);
        gent.addProjectToOffice(javaNew);

        antwerp.addProjectToOffice(pythonNew);
        antwerp.addProjectToOffice(javaCrabbed);


        listOfProjects.forEach(project -> projectRepository.addProject(project));
        listOfDevelopers.forEach(developer -> developerRepository.addDeveloper(developer));
        officeList.forEach(office -> officeRepository.addOffice(office));

        logger.info("Seeding completed!");

    }

    @Override
    public void run(String... args) throws Exception {
        seed();
    }
}
