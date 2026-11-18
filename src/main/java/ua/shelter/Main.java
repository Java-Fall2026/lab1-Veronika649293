package ua.shelter;

import ua.shelter.model.*;
import ua.shelter.util.ShelterUtils;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. Створення record-об'єктів та звернення до компонентів ===");
        Species lion = Species.of("  spec-lion ", "African Lion", Diet.CARNIVORE);
        Keeper keeper = Keeper.of("kpr-001", "John Doe", LocalDate.of(2018, 5, 10));
        System.out.println("Код виду (компонент record): " + lion.speciesCode());
        System.out.println("Назва виду (компонент record): " + lion.commonName());
        System.out.println("Бейдж доглядача: " + keeper.employeeBadge());

        System.out.println("\n=== 2. Робота з enum: values(), власне поле та valueOf у try/catch ===");
        for (Diet d : Diet.values()) {
            System.out.println("Тип раціону: " + d + " -> базова норма корму: " + d.getBaseFoodKgPerDay() + " кг/день");
        }
        try {
            Diet invalidDiet = Diet.valueOf("VEGAN_ONLY");
            System.out.println(invalidDiet);
        } catch (IllegalArgumentException e) {
            System.out.println("Перехоплено помилку Diet.valueOf: " + e.getMessage());
        }

        System.out.println("\n=== 3. Два switch-вирази в роботі ===");
        System.out.println("Рекомендація щодо раціону (" + lion.diet() + "): " + ShelterUtils.describeDietSchedule(lion.diet()));
        Animal simba = new Animal("CHIP-999123", "Simba", lion, LocalDate.of(2021, 3, 15), HealthStatus.QUARANTINE);
        System.out.println("Доступна дія для статусу (" + simba.getHealth() + "): " + ShelterUtils.getAvailableAction(simba.getHealth()));

        System.out.println("\n=== 4. Невалідний record -> IllegalArgumentException із компактного конструктора ===");
        try {
            Keeper invalidKeeper = Keeper.of("   ", "Jane Doe", LocalDate.of(2020, 1, 1));
            System.out.println(invalidKeeper);
        } catch (IllegalArgumentException e) {
            System.out.println("Компактний конструктор відхилив створення record: " + e.getMessage());
        }

        System.out.println("\n=== 5. Два однакові record-об'єкти: згенеровані equals та hashCode ===");
        Species lionCopy = new Species("SPEC-LION", "African Lion", Diet.CARNIVORE);
        System.out.println("lion == lionCopy: " + (lion == lionCopy));
        System.out.println("lion.equals(lionCopy): " + lion.equals(lionCopy));
        System.out.println("lion.hashCode() == lionCopy.hashCode(): " + (lion.hashCode() == lionCopy.hashCode()));

        System.out.println("\n=== 6. Вивід record через згенерований toString() ===");
        System.out.println(lion);
        System.out.println(keeper);
    }
}