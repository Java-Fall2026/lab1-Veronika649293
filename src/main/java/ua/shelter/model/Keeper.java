package ua.shelter.model;

import ua.common.BaseEntity;
import ua.shelter.util.ShelterUtils;

import java.time.LocalDate;
import java.util.Objects;

public class Keeper extends BaseEntity {

    private final String employeeBadge;
    private final String fullName;
    private final LocalDate hireDate;

    private Keeper(String employeeBadge, String fullName, LocalDate hireDate) {
        super();
        this.employeeBadge = ShelterUtils.checkAndNormalizeCode(employeeBadge, "Employee badge");
        this.fullName = ShelterUtils.checkAndNormalizeName(fullName, "Full name");
        ShelterUtils.validateHireDate(hireDate);
        this.hireDate = hireDate;
    }

    public static Keeper of(String employeeBadge, String fullName, LocalDate hireDate) {
        return new Keeper(employeeBadge, fullName, hireDate);
    }

    public String getEmployeeBadge() {
        return employeeBadge;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keeper keeper = (Keeper) o;
        return Objects.equals(employeeBadge, keeper.employeeBadge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeBadge);
    }

    @Override
    public String toString() {
        return "Keeper{" +
                "employeeBadge='" + employeeBadge + '\'' +
                ", fullName='" + fullName + '\'' +
                ", hireDate=" + hireDate +
                ", createdAt=" + createdAt +
                '}';
    }
}