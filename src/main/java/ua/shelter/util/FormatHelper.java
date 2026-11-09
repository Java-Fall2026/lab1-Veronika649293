package ua.shelter.util;

class FormatHelper {

    private FormatHelper() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    static String normalize(String value) {
        if (value == null) {
            return null;
        }
        return value.trim();
    }

    static String normalizeUpper(String value) {
        if (value == null) {
            return null;
        }
        return value.trim().toUpperCase();
    }
}