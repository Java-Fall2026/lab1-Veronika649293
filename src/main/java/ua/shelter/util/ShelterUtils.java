package ua.shelter.util;

import ua.shelter.model.Animal;
import ua.shelter.model.CareSchedule;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ShelterUtils {

    static final List<String> ALLOWED_DIETS = List.of("CARNIVORE", "HERBIVORE", "OMNIVORE");
    static final List<String> ALLOWED_HEALTH_STATUSES = List.of("HEALTHY", "UNDER_TREATMENT", "QUARANTINE");
    static final LocalDate MIN_HIRE_DATE = LocalDate.of(2000, 1, 1);
    static final int MIN_FEEDINGS = 1;
    static final int MAX_FEEDINGS = 6;

    private ShelterUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static String checkAndNormalizeCode(String code, String fieldName) {
        ValidationHelper.requireNotBlank(code, fieldName);
        return FormatHelper.normalizeUpper(code);
    }

    public static String checkAndNormalizeName(String name, String fieldName) {
        ValidationHelper.requireNotBlank(name, fieldName);
        return FormatHelper.normalize(name);
    }

    public static String checkAndNormalizeDiet(String diet) {
        String normalized = FormatHelper.normalizeUpper(diet);
        ValidationHelper.requireInList(normalized, ALLOWED_DIETS, "Diet");
        return normalized;
    }

    public static String checkAndNormalizeHealth(String health) {
        String normalized = FormatHelper.normalizeUpper(health);
        ValidationHelper.requireInList(normalized, ALLOWED_HEALTH_STATUSES, "Health status");
        return normalized;
    }

    public static void validateHireDate(LocalDate hireDate) {
        ValidationHelper.requireDateBetween(hireDate, MIN_HIRE_DATE, LocalDate.now(), "Hire date");
    }

    public static void validateBirthDate(LocalDate birthDate) {
        ValidationHelper.requireDateNotAfter(birthDate, LocalDate.now(), "Birth date");
    }

    public static void validateFeedingsPerDay(int feedings) {
        ValidationHelper.requireInRange(feedings, MIN_FEEDINGS, MAX_FEEDINGS, "Feedings per day");
    }

    public static void validateScheduleDates(Animal animal, LocalDate startDate, LocalDate endDate) {
        ValidationHelper.requireNotNull(animal, "Animal");
        ValidationHelper.requireNotNull(startDate, "Start date");
        ValidationHelper.requireDateNotBefore(startDate, animal.getBirthDate(), "Start date (must be >= animal birth date)");

        if (endDate != null) {
            ValidationHelper.requireDateNotBefore(endDate, startDate, "End date (must be >= start date)");
        }
    }

    public static void validateKeeperHireDateForSchedule(LocalDate hireDate, LocalDate startDate) {
        ValidationHelper.requireNotNull(hireDate, "Keeper hire date");
        ValidationHelper.requireDateNotBefore(startDate, hireDate, "Start date (must be >= keeper hire date)");
    }

    public static int animalAgeMonths(Animal animal) {
        ValidationHelper.requireNotNull(animal, "Animal");
        Period period = Period.between(animal.getBirthDate(), LocalDate.now());
        return period.getYears() * 12 + period.getMonths();
    }

    public static long careDays(CareSchedule schedule) {
        ValidationHelper.requireNotNull(schedule, "CareSchedule");
        if (schedule.getEndDate() == null) {
            return -1;
        }
        return ChronoUnit.DAYS.between(schedule.getStartDate(), schedule.getEndDate());
    }
}