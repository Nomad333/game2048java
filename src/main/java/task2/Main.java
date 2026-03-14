package task2;

import com.github.javafaker.Faker;
import task2.driver.Driver;
import task2.trip.Address;
import task2.trip.Trip;
import task2.vehicle.DefaultVehicle;
import task2.vehicle.Vehicle;
import task2.vehicle.VehicleStatus;

import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    /*
    Завдання 2. Використовуючи фреймворки Lombok та JUnit5 виконайте наступне завдання:
    Розробити систему "Автобаза".
    Щодня з'являються випадково заявки на перевезення, які
    Диспетчер розподіляє на Рейси між Водіями та призначає для цього Автомобіль.
    Заявка на рейс повинна містити пункт призначення, кількість та тип вантажу.
    Водій на рейс визначається виходячи із стажу, необхідного для перевезення зазначеного
    у заявці товару, складності керування Автомобілем та Довжини шляху.
    Автомобіль на рейс визначається з принципу оптимізації ваги, що перевозиться, і вантажопідйомності автомобіля.
    Під час подорожі автомобіль може зламатися. Водій може зробити заявку на ремонт.
    Після досягнення кінцевого пункту водій отримує певну суму виплат. При цьому
    звільняється автомобіль та водій, і вони доступні для виконання наступних заявок.
    Водій робить відмітку про виконання Рейсу та стан Автомобіля.
    Під час розробки програми використовувати контейнерні класи стандартної бібліотеки.
    Зробити логування усіх подорожей у файл через файлові потоки. Зробити збір статистики
    скільки вантажів було перевезено кожним водієм, скільки вантажів перевезено до конкретного
     пункту призначення, водії з найбільшим заробітком тощо.
    Протестуйте класи та методи, що реалізують  логіку роботи зі створеними класами моделі
     даних за допомогою тестів фреймворку JUnit5.
     */
    public static void main(String[] args) {
        Faker faker = new Faker();

        List<Vehicle> vehicles = new ArrayList<>();
        List<Driver> drivers = new ArrayList<>();
        Queue<Trip> trips = new ArrayDeque<>();

        for (int i = 0; i < 10; i++) {
            vehicles.add(new DefaultVehicle(faker.funnyName().name(), faker.number().numberBetween(100, 1000), VehicleStatus.OK));
            drivers.add(new Driver(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().phoneNumber(), faker.number().numberBetween(1, 20)));
            trips.add(new Trip(faker.number().randomDouble(2, 100, 1000), new Address(faker.address().fullAddress()), faker.number().numberBetween(1, 100)));
        }

        var dispatcher = DispatcherService.builder()
                .drivers(drivers)
                .vehicles(vehicles)
                .pendingTrips(trips)
                .build();

        doSomething(dispatcher);
    }

    public static void doSomething(DispatcherService dispatcherService) {
        while (dispatcherService.assignNextTrip()) {

        }
        dispatcherService.getTripsInProgress().forEach(System.out::println);
        dispatcherService.printStatistics();

        var tripsInProgress = dispatcherService.getTripsInProgress();
        for (Trip trip : new ArrayList<>(tripsInProgress)) {
            dispatcherService.completeTrip(trip);
        }
        dispatcherService.printStatistics();
    }
}
