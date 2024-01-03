package com.programming3.devcompany.domain;

public enum Position {
    TRAINEE,
    JUNIOR,
    MIDDLE,
    SENIOR,
    LEAD,
    ;


    @Override
    public String toString() {
        switch (this) {
            case TRAINEE -> {
                return  "Trainee";
            }
            case JUNIOR -> {
                return "Junior";
            }
            case MIDDLE -> {
                return "Middle";
            }
            case SENIOR -> {
                return "Senior";
            }
            case LEAD -> {
                return "Lead GOD";
            }
            default -> {
                return null;
            }
        }
    }


}
