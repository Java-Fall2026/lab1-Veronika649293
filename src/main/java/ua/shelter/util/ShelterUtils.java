package ua.shelter.util;

import ua.shelter.model.Animal;
import ua.shelter.model.CareSchedule;
import ua.shelter.model.Diet;
import ua.shelter.model.HealthStatus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Public utility class providing validation, formatting, switch expressions, and domain calculations.
 */
public class ShelterUtils {

    private ShelterUtils() {
    }

    public static String checkAndNormalizeCode(String code, String fieldName) {
        String normalized = FormatHelper.normalizeUpper(code);
        ValidationHelper.validateCode(normalized, fieldName);
        return normalized;
    }

    public static String checkAndNormalizeName(String name, String fieldName) {
        String normalized = FormatHelper.normalizeText(name);
        ValidationHelper.validateName(normalized, fieldName);
        return normalized;
    }

    public static void validateBirthDate(LocalDate birthDate) {
        ValidationHelper.validateBirthDate(birthDate);
    }

    public static void validateHireDate(LocalDate hireDate) {
        ValidationHelper.validateHireDate(hireDate);
    }

    public static void validateScheduleDates(Animal animal, LocalDate startDate, LocalDate endDate) {
        ValidationHelper.validateScheduleDates(animal, startDate, endDate);
    }

    public static void validateKeeperHireDateForSchedule(LocalDate hireDate, LocalDate startDate) {
        ValidationHelper.validateKeeperHireDateForSchedule(hireDate, startDate);
    }

    public static void validateFeedingsPerDay(int feedingsPerDay) {
        ValidationHelper.validateFeedingsPerDay(feedingsPerDay);
    }

    /**
     * Calculated method #1: calculates animal age in full months.
     */
    public static long ageInMonths(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("Animal must not be null, got: null");
        }
        return ChronoUnit.MONTHS.between(animal.getBirthDate(), LocalDate.now());
    }

    /**
     * Calculated method #2: calculates care duration in days (-1 if ongoing).
     */
    public static long careDays(CareSchedule schedule) {
        if (schedule == null) {
            throw new IllegalArgumentException("CareSchedule must not be null, got: null");
        }
        if (schedule.getEndDate() == null) {
            return -1;
        }
        return ChronoUnit.DAYS.between(schedule.getStartDate(), schedule.getEndDate());
    }

    /**
     * Switch expression #1: maps Diet enum without default branch.
     */
    public static String describeDietSchedule(Diet diet) {
        if (diet == null) {
            throw new IllegalArgumentException("Diet must not be null, got: null");
        }
        return switch (diet) {
            case CARNIVORE -> "High-protein meat ration twice daily";
            case HERBIVORE -> "Fresh hay, greens, and vegetables continuously";
            case OMNIVORE -> "Balanced mix of meat, grains, and vegetables";
        };
    }

    /**
     * Switch expression #2: determines behavioral action based on animal health status.
     */
    public static String getAvailableAction(HealthStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Health status must not be null, got: null");
        }
        return switch (status) {
            case HEALTHY -> "Open for public adoption and general walks";
            case UNDER_TREATMENT -> "Restricted to veterinary care and prescribed medication";
            case QUARANTINE -> "Strict isolation; staff protective gear required";
        };
    }
}