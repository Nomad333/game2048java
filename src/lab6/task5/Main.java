package lab6.task5;

import lab6.task5.buildings.Building;
import lab6.task5.buildings.builder.ListBuildingsBuilder;
import lab6.task5.buildings.subclass.Hospital;
import lab6.task5.buildings.subclass.House;
import lab6.task5.buildings.subclass.School;
import lab6.task5.buildings.subclass.Store;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    /*
        На вулиці мають бути присутніми будинки різного типу. Наприклад, житлові будинки,
    магазини, лікарні, школи. Передбачити тестову ініціалізацію кожного об'єкта та вулиці загалом.
    Кожен будинок повинен мати певну адресу в межах вулиці. Крім того, кожен тип будинку
    повинен мати свою сукупність полів. Наприклад, має бути тип магазину та перелік відділів
    у ньому (наприклад приватний маленький магазин повинен мати 1 відділ, супермаккет 5 відділів),
    кількість мешканців у житловому будинку, кількість учнів та рівень акредитації у школі
    (загальноосвітня, гімназія, ліцей тощо). Кількість учнів у школі прив'язати до рівня акредитації
    та випадково обирати у певному діапазоні.
         Передбачити можливість встановлення адреси кожного об'єкта як через конструктор, і
    через сеттер. Передбачити можливість додавання нового будинку на вулицю та видалення
    будинку. Також передбачити віртуальний метод, який прийматиме рядок та на його основі
    встановлюватиме поля об'єкта. Зробити висновок інформації про кожну оселю в консоль.
    Зробити виведення інформації вулицею в консоль. Зробити спосіб. який для випадково
    обраного житлового будинку знаходить у заданій околиці (певну кількість будинків від
    адреси будинку) усі магазини, що мають відділ заданого типу.
        Генерацію тестового покриття зробити за допомогою статичної фабрики.
    Взаємодія з користувачем зробити через меню, при цьому передбачити окремий клас.
    У роботі використовувати інтерфейси, абстрактні класи (за потреби) та перерахування.
    За потреби використовувати механізм обробки винятків.

     */
    public static void main(String[] args) throws Exception {
        var builder = new ListBuildingsBuilder();
        var buildings = builder
                .addBuildings(School.class, 3)
                .addBuildings(House.class, 5)
                .addBuildings(Store.class, 10)
                .addBuildings(Hospital.class, 1)
                .build();
        var manager = new BuildingsManager(buildings);

        dialog(manager);
    }

    public static void dialog(BuildingsManager manager) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    1. Cгенерувати випадкову будівлю
                    2. Зробити висновок про кожну оселю
                    3. Вивести інформацію про будівлі за вулицею
                    4. getRandomHouseAndFindLocalStoresByLevel
                    5. Показати всі будівлі
                    0. Вихід
                    """);

            int cmd = scanner.nextInt();
            scanner.nextLine();

            switch (cmd) {
                case 1 -> {
                    List<Callable<Building>> tasks = List.of(
                            new House.HouseFactory()::create,
                            new School.SchoolFactory()::create,
                            new Store.StoreFactory()::create,
                            new Hospital.HospitalFactory()::create
                    );

                    var building = tasks.get(ThreadLocalRandom.current().nextInt(tasks.size())).call();
                    manager.addBuilding(building);
                    System.out.println("Added building: " + building);
                }
                case 2 -> {
                    System.out.println(manager.getBuildingsByType(House.class));
                }
                case 3 -> {
                    System.out.print("Enter street: ");
                    String street = scanner.nextLine();
                    System.out.println(manager.getBuildingsBy(Building.class, b -> b.getStreet().equals(street)));
                }
                case 4 -> {
                    System.out.print("Enter department: ");
                    int level = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(manager.getRandomHouseAndFindLocalStoresByLevel(level));
                }
                case 5 -> {
                    manager.getBuildings().forEach(System.out::println);
                }
                case 0 -> {
                    return;
                }
            }
            System.out.println("-".repeat(20));
        }
    }

}
