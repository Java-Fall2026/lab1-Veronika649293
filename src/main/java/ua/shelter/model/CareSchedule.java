package ua.shelter.model;

public class CareSchedule {
    
}
package ua.shelter.model;

import ua.common.BaseEntity;
import ua.shelter.util.ShelterUtils;

import java.time.LocalDate;
import java.util.Objects;

public class CareSchedule extends BaseEntity {

    private final Animal animal;
    private final Keeper keeper;
    private final LocalDate startDate;
    private LocalDate endDate;
    private int feedingsPerDay;

    public CareSchedule(Animal animal, Keeper keeper, LocalDate startDate, LocalDate endDate, int feedingsPerDay) {
        super();
        if (animal == null) {
            throw new IllegalArgumentException("Animal must not be null, got: null");
        }
        if (keeper == null) {
            throw new IllegalArgumentException("Keeper must not be null, got: null");
        }
        this.animal = animal;
        this.keeper = keeper;

        ShelterUtils.validateScheduleDates(animal, startDate, endDate);
        ShelterUtils.validateKeeperHireDateForSchedule(keeper.getHireDate(), startDate);

        this.startDate = startDate;
        setEndDate(endDate);
        setFeedingsPerDay(feedingsPerDay);
    }

    public Animal getAnimal() {
        return animal;
    }

    public Keeper getKeeper() {
        return keeper;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public final void setEndDate(LocalDate endDate) {
        if (endDate != null) {
            ShelterUtils.validateScheduleDates(animal, startDate, endDate);
        }
        this.endDate = endDate;
    }

    public int getFeedingsPerDay() {
        return feedingsPerDay;
    }

    public final void setFeedingsPerDay(int feedingsPerDay) {
        ShelterUtils.validateFeedingsPerDay(feedingsPerDay);
        this.feedingsPerDay = feedingsPerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CareSchedule schedule = (CareSchedule) o;
        return Objects.equals(animal, schedule.animal) &&
                Objects.equals(keeper, schedule.keeper) &&
                Objects.equals(startDate, schedule.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animal, keeper, startDate);
    }

    @Override
    public String toString() {
        return "CareSchedule{" +
                "animal=" + animal.getChipNumber() +
                ", keeper=" + keeper.getEmployeeBadge() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", feedingsPerDay=" + feedingsPerDay +
                ", createdAt=" + createdAt +
                '}';
    }
}