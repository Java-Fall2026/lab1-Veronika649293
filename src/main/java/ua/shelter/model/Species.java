package ua.shelter.model;

import ua.common.BaseEntity;
import ua.shelter.util.ShelterUtils;

import java.util.Objects;

public class Species extends BaseEntity {

    private final String speciesCode;
    private final String commonName;
    private final String diet;

    private Species(String speciesCode, String commonName, String diet) {
        super();
        this.speciesCode = ShelterUtils.checkAndNormalizeCode(speciesCode, "Species code");
        this.commonName = ShelterUtils.checkAndNormalizeName(commonName, "Common name");
        this.diet = ShelterUtils.checkAndNormalizeDiet(diet);
    }

    public static Species of(String speciesCode, String commonName, String diet) {
        return new Species(speciesCode, commonName, diet);
    }

    public String getSpeciesCode() {
        return speciesCode;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getDiet() {
        return diet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Species species = (Species) o;
        return Objects.equals(speciesCode, species.speciesCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speciesCode);
    }

    @Override
    public String toString() {
        return "Species{" +
                "speciesCode='" + speciesCode + '\'' +
                ", commonName='" + commonName + '\'' +
                ", diet='" + diet + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}