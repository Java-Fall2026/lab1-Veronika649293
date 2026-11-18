package ua.shelter.util;

/**
 * Package-private helper class for string normalization and formatting.
 */
class FormatHelper {

    private FormatHelper() {
    }

    static String normalizeUpper(String input) {
        return input == null ? null : input.trim().toUpperCase();
    }

    static String normalizeText(String input) {
        return input == null ? null : input.trim().replaceAll("\\s+", " ");
    }
}