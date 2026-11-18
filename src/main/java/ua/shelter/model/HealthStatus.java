package ua.shelter.model;

/**
 * Represents the medical status of an animal in the shelter.
 */
public enum HealthStatus {
    HEALTHY(true),
    UNDER_TREATMENT(false),
    QUARANTINE(false);

    private final boolean readyForAdoption;

    HealthStatus(boolean readyForAdoption) {
        this.readyForAdoption = readyForAdoption;
    }

    public boolean isReadyForAdoption() {
        return readyForAdoption;
    }
}