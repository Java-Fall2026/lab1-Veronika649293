package ua.shelter;

import ua.shelter.model.Animal;
import ua.shelter.model.CareSchedule;
import ua.shelter.model.Keeper;
import ua.shelter.model.Species;
import ua.shelter.util.ShelterUtils;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== 1. Створення об'єктів конструктором та фабричним методом ===");
        Species lionSpecies = Species.of("  spec-lion  ", " African Lion ", "carnivore");
        Keeper keeperJohn = Keeper.of("  kpr-001 ", "  John Doe ", LocalDate.of(2018, 5, 10));
        Animal simba = new Animal("  chip-999123 ", " Simba ", lionSpecies, LocalDate.of(2021, 3, 15), "healthy");
        CareSchedule schedule = new CareSchedule(simba, keeperJohn, LocalDate.of(2022, 1, 1), null, 3);

        System.out.println("Об'єкти успішно створено!");

        System.out.println("\n=== 2. Нормалізація в дії ===");
        System.out.println("Species Code normalized: '" + lionSpecies.getSpeciesCode() + "'");
        System.out.println("Diet normalized: '" + lionSpecies.getDiet() + "'");
        System.out.println("Keeper Badge normalized: '" + keeperJohn.getEmployeeBadge() + "'");
        System.out.println("Animal Health normalized: '" + simba.getHealth() + "'");

        System.out.println("\n=== 3. Три різні порушення правил у try/catch ===");

        try {
            System.out.print("Тест 1 (невалидна раціон-дієта): ");
            Species.of("SPEC-DOG", "Dog", "FAST_FOOD");
        } catch (IllegalArgumentException e) {
            System.out.println("Успішно перехоплено: " + e.getMessage());
        }

        try {
            System.out.print("Тест 2 (дата найму з майбутнього): ");
            Keeper.of("KPR-999", "Future Worker", LocalDate.now().plusDays(5));
        } catch (IllegalArgumentException e) {
            System.out.println("Успішно перехоплено: " + e.getMessage());
        }

        try {
            System.out.print("Тест 3 (годувань на день > 6): ");
            new CareSchedule(simba, keeperJohn, LocalDate.of(2022, 1, 1), null, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Успішно перехоплено: " + e.getMessage());
        }

        System.out.println("\n=== 4. Спроба зіпсувати коректний об'єкт сеттером ===");
        try {
            simba.setHealth("SUPER_POWRED");
        } catch (IllegalArgumentException e) {
            System.out.println("Сеттер відхилив некоректний статус: " + e.getMessage());
        }
        System.out.println("Статус здоров'я залишився: " + simba.getHealth());

        System.out.println("\n=== 5. Порівняння ==, equals та hashCode ===");
        Animal simbaClone = new Animal("CHIP-999123", "Simba Copy", lionSpecies, LocalDate.of(2021, 3, 15), "QUARANTINE");
        Animal nala = new Animal("CHIP-888456", "Nala", lionSpecies, LocalDate.of(2021, 6, 20), "HEALTHY");

        System.out.println("simba == simbaClone: " + (simba == simbaClone));
        System.out.println("simba.equals(simbaClone): " + simba.equals(simbaClone));
        System.out.println("simba.hashCode() == simbaClone.hashCode(): " + (simba.hashCode() == simbaClone.hashCode()));
        System.out.println("simba.equals(nala): " + simba.equals(nala));

        System.out.println("\n=== 6. Виклик обчислюваних методів ===");
        System.out.println("Вік тварини Simba (у місяцях): " + ShelterUtils.animalAgeMonths(simba));
        System.out.println("Тривалість опіки (поточна): " + ShelterUtils.careDays(schedule));

        schedule.setEndDate(LocalDate.of(2023, 1, 1));
        System.out.println("Тривалість опіки (завершеної): " + ShelterUtils.careDays(schedule) + " днів");

        System.out.println("\n=== 7. Вивід усіх сутностей через toString() ===");
        System.out.println(lionSpecies);
        System.out.println(keeperJohn);
        System.out.println(simba);
        System.out.println(schedule);

        System.out.println("\n=== 8. Доступ до модифікаторів доступу (демонстрація недоступності) ===");
        // ValidationHelper helper = new ValidationHelper(); // НЕ КОМПІЛЮЄТЬСЯ
        // Species spec = new Species("S1", "Name", "CARNIVORE"); // НЕ КОМПІЛЮЄТЬСЯ
    }
}