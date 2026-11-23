package ua.shelter.model;

import ua.shelter.util.ShelterUtils;

/**
 * Immutable record representing an animal species.
 */
public record Species(String speciesCode, String commonName, Diet diet) {

    public Species {
        speciesCode = ShelterUtils.checkAndNormalizeCode(speciesCode, "Species code");
        commonName = ShelterUtils.checkAndNormalizeName(commonName, "Common name");
        if (diet == null) {
            throw new IllegalArgumentException("Diet must not be null, got: null");
        }
    }

    public static Species of(String speciesCode, String commonName, Diet diet) {
        return new Species(speciesCode, commonName, diet);
    }
}