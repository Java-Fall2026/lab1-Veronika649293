package ua.shelter.model;

public class Animal {
    
}
package ua.shelter.model;

import ua.common.BaseEntity;
import ua.shelter.util.ShelterUtils;

import java.time.LocalDate;
import java.util.Objects;

public class Animal extends BaseEntity {

    private final String chipNumber;
    private final String nickname;
    private final Species species;
    private final LocalDate birthDate;
    private String health;

    public Animal(String chipNumber, String nickname, Species species, LocalDate birthDate, String health) {
        super();
        this.chipNumber = ShelterUtils.checkAndNormalizeCode(chipNumber, "Chip number");
        this.nickname = ShelterUtils.checkAndNormalizeName(nickname, "Nickname");
        
        if (species == null) {
            throw new IllegalArgumentException("Species must not be null, got: null");
        }
        this.species = species;

        ShelterUtils.validateBirthDate(birthDate);
        this.birthDate = birthDate;

        setHealth(health);
    }

    public String getChipNumber() {
        return chipNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public Species getSpecies() {
        return species;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getHealth() {
        return health;
    }

    public final void setHealth(String health) {
        this.health = ShelterUtils.checkAndNormalizeHealth(health);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(chipNumber, animal.chipNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chipNumber);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "chipNumber='" + chipNumber + '\'' +
                ", nickname='" + nickname + '\'' +
                ", species=" + species.getSpeciesCode() +
                ", birthDate=" + birthDate +
                ", health='" + health + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}