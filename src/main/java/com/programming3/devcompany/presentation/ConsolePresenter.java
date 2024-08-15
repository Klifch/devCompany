package com.programming3.devcompany.presentation;

import com.programming3.devcompany.domain.Developer;
import com.programming3.devcompany.domain.Project;
import com.programming3.devcompany.service.DeveloperService;
import com.programming3.devcompany.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Profile("list")
@Component
public class ConsolePresenter {

    private final DeveloperService developerService;
    private final ProjectService projectService;

    @Autowired
    public ConsolePresenter(DeveloperService developerService, ProjectService projectService) {
        this.developerService = developerService;
        this.projectService = projectService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("\n][===========================][");
            System.out.println("\t\tSELECT AN OPTION:");
            System.out.println("][===========================][");
            System.out.println("1. Show all projects");
            System.out.println("2. Show all developers");
            System.out.println("3. Show all projects for developer");
            System.out.println("4. show all developers for project");
            System.out.println("5. Show all developers with salary lower/higher (some value)");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> showAllProjects();
                case 2 -> showAllDevelopers();
                case 3 -> {
                    System.out.print("Enter developer's id: ");
                    int id = scanner.nextInt();
                    showAllProjectsForDeveloper(id);
                }
                case 4 -> {
                    System.out.print("Enter id of the project: ");
                    int id = scanner.nextInt();
                    showAllDevelopersForProject(id);
                }
                case 5 -> {
                    System.out.println("Enter the salary: ");
                    double salary = scanner.nextDouble();
                    System.out.println("Choose 1 or 2 for higher and lower respectively:");
                    int direction = scanner.nextInt();

                    if (direction == 1) {
                        showAllDevelopersWithSalary(salary, 1);
                    } else if (direction == 2) {
                        showAllDevelopersWithSalary(salary, 2);
                    } else {
                        System.out.println("\n\tInvalid Input!");
                    }
                }
                case 6 -> {
                    System.out.println("\n\tThanks! Bye!");
                    System.exit(0);
                }
                default -> System.out.println("\n\tInvalid choice!");
            }
        }
    }

    private void showAllProjects() {
        System.out.println("\n][===========================][");
        System.out.print("\t\tAll projects:");
        System.out.println("\n][===========================][\n");
        projectService.getAllProjects().forEach(project -> System.out.println("-> " + project));
    }

    private void showAllDevelopers() {
        System.out.println("\n][===========================][");
        System.out.print("\t\tAll developers:");
        System.out.println("\n][===========================][\n");
        developerService.getAll().forEach(System.out::println);
    }

    private void showAllProjectsForDeveloper(Integer id) {
        System.out.println("\n][===========================][");
        System.out.printf("All projects for developer with id %d", id);
        System.out.println("\n][===========================][\n");

        Developer developer = developerService.getOneById(id);
        if (developer != null) {
            System.out.printf("Developer %s with id %d is a part of projects: \n",developer.getFirstName(), id);
            projectService.getProjectsForDeveloper(developer).forEach(System.out::println);
        } else {
            System.out.println("Developer with such id doesn't exist!");
        }
    }

    private void showAllDevelopersForProject(Integer id) {
        System.out.println("\n][===========================][");
        System.out.printf("All developers for project with id %d", id);
        System.out.println("\n][===========================][\n");

        Project project = projectService.getProjectById(id);
        if (project != null) {
            developerService.getAllForProject(project)
                    .forEach(developer -> System.out.printf("Developer %s with id %d\n",
                            developer.getFirstName(),
                            developer.getId()
                    ));
        } else {
            System.out.println("Project with such id doesn't exist!");
        }
    }

    private void showAllDevelopersWithSalary(Double salary, int sign) {
        if (salary <= 0) {
            System.out.println("\n\tInvalid Input!");
        } else {
            System.out.println("\n  ][===========================][");
            System.out.println("All developers with salaries in this range:");
            System.out.println("  ][===========================][\n");

            if (sign == 1) {
                developerService.findAllWithSalary(salary, 1)
                        .forEach(developer -> System.out.printf("* Developer %s with id %d and salary %f\n",
                                developer.getFirstName(),
                                developer.getId(),
                                developer.getSalary()
                        ));
            } else {
                developerService.findAllWithSalary(salary, 2)
                        .forEach(developer -> System.out.printf("* Developer %s with id %d and salary %f\n",
                                developer.getFirstName(),
                                developer.getId(),
                                developer.getSalary()
                        ));
            }
        }
    }
}
