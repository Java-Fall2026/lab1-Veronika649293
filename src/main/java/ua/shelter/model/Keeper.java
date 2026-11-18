package ua.shelter.model;

import ua.shelter.util.ShelterUtils;

import java.time.LocalDate;

/**
 * Immutable record representing a shelter keeper.
 */
public record Keeper(String employeeBadge, String fullName, LocalDate hireDate) {

    public Keeper {
        employeeBadge = ShelterUtils.checkAndNormalizeCode(employeeBadge, "Employee badge");
        fullName = ShelterUtils.checkAndNormalizeName(fullName, "Full name");
        ShelterUtils.validateHireDate(hireDate);
    }

    public static Keeper of(String employeeBadge, String fullName, LocalDate hireDate) {
        return new Keeper(employeeBadge, fullName, hireDate);
    }
}