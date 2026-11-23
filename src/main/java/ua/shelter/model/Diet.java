package ua.shelter.model;

/**
 * Represents dietary classifications for shelter animals.
 */
public enum Diet {
    CARNIVORE(2.5),
    HERBIVORE(4.0),
    OMNIVORE(1.8);

    private final double baseFoodKgPerDay;

    Diet(double baseFoodKgPerDay) {
        this.baseFoodKgPerDay = baseFoodKgPerDay;
    }

    public double getBaseFoodKgPerDay() {
        return baseFoodKgPerDay;
    }
}