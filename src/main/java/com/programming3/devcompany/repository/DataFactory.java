package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Office;
import com.programming3.devcompany.domain.Position;
import com.programming3.devcompany.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(1)
public class DataFactory implements CommandLineRunner {

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

        List<Developer> listOfDevelopers = new ArrayList<>();
        List<Project> listOfProjects = new ArrayList<>();
        List<Office> officeList = new ArrayList<>();

        Project springNew = new Project("Spring2", 100500, 9101);
        Project javaNew = new Project("java40", 13330, 9091);
        Project pythonNew = new Project("python4", 10, 10982);
        Project javaCrabbed = new Project("javaCrab", 100000, 23421);

        listOfProjects.add(springNew);
        listOfProjects.add(javaCrabbed);
        listOfProjects.add(javaNew);
        listOfProjects.add(pythonNew);

        Developer ben = new Developer("Ben", 20, 2500d, "2030-03-02", Position.SENIOR);
        Developer oleksii = new Developer("oleksii", 22, 5500d, "2027-10-09", Position.JUNIOR);
        Developer anna = new Developer("Anna", 22, 2700d, "2023-10-12", Position.LEAD);
        Developer leo = new Developer("Leo", 19, 2500d, "2024-07-10", Position.JUNIOR);

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

    }

    @Override
    public void run(String... args) throws Exception {
        seed();
    }
}
