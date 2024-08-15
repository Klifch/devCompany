package com.programming3.devcompany.presentation.viewmodel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SearchViewModel {

    @NotNull(message = "Enter the name or surname.")
    @Size(min = 1, max = 15, message = "Couldn't be longer than 15 characters")
    private String nameOrSurname;

    public SearchViewModel() {
    }

    public SearchViewModel(String nameOrSurname) {
        this.nameOrSurname = nameOrSurname;
    }

    public String getNameOrSurname() {
        return nameOrSurname;
    }

    public void setNameOrSurname(String nameOrSurname) {
        this.nameOrSurname = nameOrSurname;
    }
}
