package ua.shelter.util;

import ua.shelter.model.Animal;

import java.time.LocalDate;

/**
 * Package-private helper class containing domain validation rules.
 */
class ValidationHelper {

    static final LocalDate MIN_DATE = LocalDate.of(2000, 1, 1);
    static final int MIN_FEEDINGS = 1;
    static final int MAX_FEEDINGS = 6;

    private ValidationHelper() {
    }

    static void validateCode(String code, String fieldName) {
        if (code == null || !code.matches("^[A-Z0-9-]{3,20}$")) {
            throw new IllegalArgumentException(
                    fieldName + " must match pattern ^[A-Z0-9-]{3,20}$, got: '" + code + "'"
            );
        }
    }

    static void validateName(String name, String fieldName) {
        if (name == null || name.length() < 2 || name.length() > 60) {
            throw new IllegalArgumentException(
                    fieldName + " length must be between 2 and 60 characters, got: '" + name + "'"
            );
        }
    }

    static void validateBirthDate(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        if (birthDate == null || birthDate.isBefore(MIN_DATE) || birthDate.isAfter(today)) {
            throw new IllegalArgumentException(
                    "Birth date must be between " + MIN_DATE + " and " + today + ", got: " + birthDate
            );
        }
    }

    static void validateHireDate(LocalDate hireDate) {
        LocalDate today = LocalDate.now();
        if (hireDate == null || hireDate.isBefore(MIN_DATE) || hireDate.isAfter(today)) {
            throw new IllegalArgumentException(
                    "Hire date must be between " + MIN_DATE + " and " + today + ", got: " + hireDate
            );
        }
    }

    static void validateScheduleDates(Animal animal, LocalDate startDate, LocalDate endDate) {
        if (startDate == null || startDate.isBefore(animal.getBirthDate())) {
            throw new IllegalArgumentException(
                    "Start date must not be before animal birth date (" + animal.getBirthDate() + "), got: " + startDate
            );
        }
        if (endDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException(
                    "End date must not be before start date (" + startDate + "), got: " + endDate
            );
        }
    }

    static void validateKeeperHireDateForSchedule(LocalDate hireDate, LocalDate startDate) {
        if (startDate.isBefore(hireDate)) {
            throw new IllegalArgumentException(
                    "Schedule start date must not be before keeper hire date (" + hireDate + "), got: " + startDate
            );
        }
    }

    static void validateFeedingsPerDay(int feedingsPerDay) {
        if (feedingsPerDay < MIN_FEEDINGS || feedingsPerDay > MAX_FEEDINGS) {
            throw new IllegalArgumentException(
                    "Feedings per day must be between " + MIN_FEEDINGS + " and " + MAX_FEEDINGS + ", got: " + feedingsPerDay
            );
        }
    }
}