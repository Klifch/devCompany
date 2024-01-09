package com.programming3.devcompany.presentation.viewmodel;

import jakarta.validation.constraints.*;

public class SortViewModel {

    @NotNull(message = "Required field!")
    @Min(value = 100, message = "Amount couldn't be lower than 100$!")
    @Max(value = 150000, message = "Amount couldn't be higher than 150000$!")
    private Double amount;

    @NotNull(message = "Required field!")
    private String option;

    public SortViewModel() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
