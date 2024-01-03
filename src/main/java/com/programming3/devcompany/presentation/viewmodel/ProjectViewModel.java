package com.programming3.devcompany.presentation.viewmodel;

import jakarta.validation.constraints.*;

public class ProjectViewModel {

    @NotBlank(message = "Name can not be empty !")
    @Size(min = 1, max = 15, message = "Required field 1 to 15 Symbols!")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only a-z,A-Z Symbols!")
    private String projectName;

    @NotNull(message = "Required field!")
    @Min(value = 1000, message = "Budget couldn't be lower than 1000$!")
    @Max(value = 150000, message = "Budget couldn't be higher than 150000$!")
    private Double projectBudget;

    public ProjectViewModel() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(Double projectBudget) {
        this.projectBudget = projectBudget;
    }
}
