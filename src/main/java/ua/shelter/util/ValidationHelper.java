package ua.shelter.util;

import java.time.LocalDate;
import java.util.List;

class ValidationHelper {

    private ValidationHelper() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    static void requireNotNull(Object obj, String fieldName) {
        if (obj == null) {
            throw new IllegalArgumentException(fieldName + " must not be null, got: null");
        }
    }

    static void requireNotBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be blank, got: '" + value + "'");
        }
    }

    static void requireInRange(int value, int min, int max, String fieldName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(fieldName + " must be between " + min + " and " + max + ", got: " + value);
        }
    }

    static void requireInList(String value, List<String> allowedValues, String fieldName) {
        if (value == null || !allowedValues.contains(value)) {
            throw new IllegalArgumentException(fieldName + " must be one of " + allowedValues + ", got: '" + value + "'");
        }
    }

    static void requireDateBetween(LocalDate date, LocalDate minDate, LocalDate maxDate, String fieldName) {
        requireNotNull(date, fieldName);
        if (date.isBefore(minDate) || date.isAfter(maxDate)) {
            throw new IllegalArgumentException(fieldName + " must be between " + minDate + " and " + maxDate + ", got: " + date);
        }
    }

    static void requireDateNotAfter(LocalDate date, LocalDate maxDate, String fieldName) {
        requireNotNull(date, fieldName);
        if (date.isAfter(maxDate)) {
            throw new IllegalArgumentException(fieldName + " must not be after " + maxDate + ", got: " + date);
        }
    }

    static void requireDateNotBefore(LocalDate date, LocalDate minDate, String fieldName) {
        requireNotNull(date, fieldName);
        if (date.isBefore(minDate)) {
            throw new IllegalArgumentException(fieldName + " must not be before " + minDate + ", got: " + date);
        }
    }
}