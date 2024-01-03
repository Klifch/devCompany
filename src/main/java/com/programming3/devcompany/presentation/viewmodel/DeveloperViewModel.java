package com.programming3.devcompany.presentation.viewmodel;

import com.programming3.devcompany.domain.Position;
import com.programming3.devcompany.presentation.validation.StartDateValidation;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeveloperViewModel {

    @NotBlank(message = "Name can not be empty !")
    @Size(min = 1, max = 15, message = "Required field 1 to 15 Symbols!")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only a-z,A-Z Symbols!")
    private String firstName;

    @NotBlank(message = "Last Name can not be empty !")
    @Size(min = 1, max = 15, message = "Required field 1 to 15 Symbols!")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only a-z,A-Z Symbols!")
    private String lastName;

    @NotNull(message = "Required field!")
    @Min(value = 18, message = "Should be older than 18!")
    @Max(value = 90, message = "Should be younger than 90!")
    private Integer age;

    @NotNull(message = "Required field!")
    @Min(value = 200, message = "Salary couldn't be lower than 200$!")
    @Max(value = 15000, message = "Salary couldn't be higher than 15000$!")
    private Double salary;

    @NotNull(message = "Required field!")
    @StartDateValidation(value = "2024-01-01", message = "Date should be later than 2024!")
//    @NotNull(message = "Required field!")
    private LocalDate endOfContract;

    @NotNull(message = "Required field!")
    private Position position;

    public DeveloperViewModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getEndOfContract() {
        return endOfContract;
    }

    public void setEndOfContract(LocalDate endOfContract) {
        this.endOfContract = endOfContract;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
